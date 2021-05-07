package top.parak.springtest.exten;

import top.parak.springcustom.BeanPostProcessor;
import top.parak.springcustom.Component;
import top.parak.springtest.service.UserService;

/**
 * @author KHighness
 * @since 2021-05-07
 */

@Component
public class MainBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("userService")) {
            ((UserService) bean).setName("KHighness");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
