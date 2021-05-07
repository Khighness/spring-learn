package top.parak.springcustom;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KHighness
 * @since 2021-05-06
 * @apiNote 应用上下文
 */

public class KHighnessApplicationContext {
    /** 配置类 */
    private Class configClass;

    /** 单例池 */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /** 定义池 */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /** 扩展池 */
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /** 单例作用域 */
    private final static String SINGLETON_SCOPE = "singleton";
    /**
     * 多例作用域，没有实现~
     * @deprecated
     */
    private final static String PROTOTYPE_SCOPE = "prototype";

    /**
     * 构造函数
     * @param configClass 配置类
     */
    public KHighnessApplicationContext(Class configClass) {
        this.configClass = configClass;
        scanClass(configClass);
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            if (entry.getValue().getScope().equals(SINGLETON_SCOPE)) {
                String beanName = entry.getKey();
                BeanDefinition beanDefinition = entry.getValue();
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    /**
     * 根据{@link top.parak.springcustom.ComponentScan}，获取扫描路径，类加载器加载路径下的所有class文件，
     * 解析{@link top.parak.springcustom.Component}和{@link top.parak.springcustom.Scope}，
     * 将结果封装成{@link top.parak.springcustom.BeanDefinition}对象，存入bean的定义池{@code beanDefinitionMap}
     * @param configClass 配置类
     */
    private void scanClass(Class configClass) {
        // 获取扫描路径
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String packagePath = componentScanAnnotation.value();
        String resourcePath = packagePath.replace(".", "/");
        // 扫描路径文件
        ClassLoader classLoader = KHighnessApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(resourcePath);
        if (resource != null) {
            File file = new File(resource.getFile());
            List<File> classList = new ArrayList<>();
            searchAllFile(file, classList);
            classList.forEach(
                classFile -> {
                    String fileAbsolutePath = classFile.getAbsolutePath().replace("\\",".");
                    int begin = fileAbsolutePath.lastIndexOf(packagePath), end = fileAbsolutePath.lastIndexOf(".class");
                    String classAbsolutePath = fileAbsolutePath.substring(begin, end);
                    try {
                        Class<?> clazz = classLoader.loadClass(classAbsolutePath);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) { // 特殊处理：判断这个类是否实现了BeanPostProcessor接口
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                                beanPostProcessors.add(instance);
                            } else { // 普通bean：封装beanDefinition: 名称beanName, 作用域singleton/prototype
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setClazz(clazz);
                                Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                                String beanName = componentAnnotation.value();
                                // 没有自定义beanName，默认使用className(小驼峰)
                                if (beanName.equals("")) {
                                    char[] nameArray = clazz.getSimpleName().toCharArray();
                                    nameArray[0] = Character.toLowerCase(nameArray[0]);
                                    beanName = new String(nameArray);
                                }
                                // 是否设置作用域，默认为singleton
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                } else {
                                    beanDefinition.setScope(SINGLETON_SCOPE);
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            );
        }
    }

    /**
     * 递归统计文件夹下的所有class文件
     * @param file 文件目录
     * @param list 文件数组
     */
    private void searchAllFile(File file, List<File> list) {
        if (!file.isDirectory()) {
            if (file.getName().endsWith(".class"))
                list.add(file);
            return;
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            searchAllFile(f, list);
        }
    }

    /**
     * 根据bean定义信息创建bean
     * @param beanDefinition bean定义信息
     * @return bean
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        final Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getConstructor().newInstance();
            // 依赖注入
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Autowired autowiredAnnotation = declaredField.getAnnotation(Autowired.class);
                    String fieldName = declaredField.getName();
                    Object fieldBean = getBean(fieldName);
                    if (fieldBean == null && autowiredAnnotation.required()) {
                        throw new NullPointerException(String.format("Required field %s can not be found", fieldName));
                    }
                    declaredField.setAccessible(true);
                    declaredField.set(instance, fieldBean);
                }
            }

            // Aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            // 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            // 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("The class is null");
    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName))
            throw new NullPointerException(String.format("Bean %s does not exist", beanName));
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals(SINGLETON_SCOPE))
            return singletonObjects.get(beanName);
        return null;
    }

}
