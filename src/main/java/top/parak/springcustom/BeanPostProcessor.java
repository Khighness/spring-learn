package top.parak.springcustom;

/**
 * @author KHighness
 * @since 2021-05-07
 * @apiNote bean的初始化扩展接口
 */

public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
