package top.parak.springlearn.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;
import top.parak.springlearn.common.KHighnessExecutingLog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author KHighness
 * @since 2021-04-01
 */

@Component
public class UserService implements
        /* 业务接口 */  UserInterface,
        /* Bean接口 */ BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        /* 环境接口 */ EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, MessageSourceAware, ApplicationEventPublisherAware, ApplicationContextAware,
        /* 后置接口 */ InitializingBean, DisposableBean {

    private String name;

    @Autowired
    public UserService() {
        KHighnessExecutingLog.info("构造函数: UserService.UserService() ");
    }

    public void setName(String name) {
        this.name = name;
        KHighnessExecutingLog.info("set函数: UserService.setName() ");
    }

    public void initMethod() {
        KHighnessExecutingLog.info("自定义初始化: UserService.initMethod()");
    }

    public void destroyMethod() {
        KHighnessExecutingLog.info("自定义销毁: UserService.destroyMethod()");
    }

    /**
     * 构造之后
     */
    @PostConstruct
    public void postConstruct() {
        KHighnessExecutingLog.info("构造之后: @PostConstruct postConstruct()");
    }

    /**
     * 销毁之前
     */
    @PreDestroy
    public void preDestroy() {
        KHighnessExecutingLog.info("销毁之前: @PreDestroy preDestroy()");
    }

    /**
     * 传入bean名称
     * @see BeanNameAware
     */
    @Override
    public void setBeanName(String s) {
        KHighnessExecutingLog.info("传入bean名称: BeanNameAware.setBeanName()");
    }

    /**
     * 传入bean类加载器
     * @see BeanClassLoaderAware
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        KHighnessExecutingLog.info("传入bean类加载器: BeanClassLoaderAware.setBeanClassLoader()");
    }

    /**
     * 传入bean工厂
     * @see BeanFactoryAware
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        KHighnessExecutingLog.info("传入bean工厂: BeanFactoryAware.setBeanFactory()");
    }

    /**
     * 传入运行时环境
     * @see EnvironmentAware
     */
    @Override
    public void setEnvironment(Environment environment) {
        KHighnessExecutingLog.info("传入运行时环境: EnvironmentAware.setEnvironment()");
    }

    /**
     * 传入文件解析器
     * @see EmbeddedValueResolverAware
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        KHighnessExecutingLog.info("传入文件解析器: EmbeddedValueResolverAware.setEmbeddedValueResolver()");
    }

    /**
     * 传入资源加载器
     * @see ResourceLoaderAware
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        KHighnessExecutingLog.info("传入资源加载器: ResourceLoaderAware.setResourceLoader()");
    }

    /**
     * 传入事件发布器
     * @see ApplicationEventPublisherAware
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        KHighnessExecutingLog.info("传入事件发布器: ApplicationEventPublisherAware.setApplicationEventPublisher()");
    }

    /**
     * 传入语言国际化
     * @see MessageSourceAware
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        KHighnessExecutingLog.info("传入语言国际化: MessageSourceAware.setMessageSource()");
    }

    /**
     * 传入应用上下文
     * @see ApplicationContextAware
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        KHighnessExecutingLog.info("传入应用上下文: ApplicationContextAware.setApplicationContext()");
    }

    /**
     * 初始化
     * <p>如名，属性设置之后执行</p>
     * @see InitializingBean
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        KHighnessExecutingLog.info("初始化: InitializingBean.afterPropertiesSet()");
    }

    /**
     * 销毁bean
     * @see DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        KHighnessExecutingLog.info("销毁bean: DisposableBean.destroy()");
    }

    /**
     * 实现业务接口
     * @see UserInterface
     */
    @Override
    public void test() {
        KHighnessExecutingLog.info("业务逻辑: UserInterface.test() " + this.name);
    }

}
