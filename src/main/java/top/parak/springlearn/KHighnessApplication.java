package top.parak.springlearn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.parak.springlearn.config.Config;
import top.parak.springlearn.service.UserInterface;

/**
 * @author KHighness
 * @since 2021-03-30
 */

public class KHighnessApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        UserInterface userInterface = (UserInterface) applicationContext.getBean("userService");
        userInterface.test();
        applicationContext.close();
    }
}

// (1) xml
//    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//    Object user = applicationContext.getBean("user");
//    System.out.println(user.toString());

// (2) @bean / @Component
//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
//    Object user = applicationContext.getBean("user");
//    System.out.println(user.toString());

// (3) BeanDefinition
//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//    beanDefinition.setBeanClass(User.class);
//    beanDefinition.setSource(new User());
//    applicationContext.registerBeanDefinition("user", beanDefinition);
//    applicationContext.refresh();
//    User user = applicationContext.getBean("user", User.class);
//    System.out.println(user.toString());

// (4) FactoryBean
//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//    beanDefinition.setBeanClass(KHighnessFactoryBean.class);
//    beanDefinition.setSource(new User());
//    applicationContext.registerBeanDefinition("user", beanDefinition);
//    applicationContext.refresh();
//    User user = applicationContext.getBean("user", User.class);
//    System.out.println(user.toString());

// (5) Suppier
//    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//    applicationContext.registerBean(User.class, new Supplier<User>() {
//        public User get() {
//            return new User();
//        }
//    });
//    applicationContext.refresh();
//    User user = applicationContext.getBean(User.class);
//    System.out.println(user.toString());

// (6) BeanFactory
//    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//    beanDefinition.setBeanClass(User.class);
//    beanFactory.registerBeanDefinition("user", beanDefinition);
//    User user = beanFactory.getBean("user", User.class);
//    System.out.println(user);
