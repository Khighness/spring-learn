package top.parak.springlearn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import top.parak.springlearn.common.KHighnessExecutingLog;
import top.parak.springlearn.service.UserService;

import javax.annotation.PostConstruct;
import java.lang.reflect.*;

/**
 * @author KHighness
 * @since 2021-04-01
 */

@Component
public class KHighnessAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.equals(UserService.class)) {
            KHighnessExecutingLog.info("实例化前: InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()");
        }
        return null;
    }

    /**
     * 返回false则终止bean属性注入
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(UserService.class)) {
            KHighnessExecutingLog.info("实例化后: InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation() ");
        }
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            KHighnessExecutingLog.info("初始化前: InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization()");
            for (Method method : bean.getClass().getMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            KHighnessExecutingLog.info("初始化后: InstantiationAwareBeanPostProcessor.postProcessAfterInitialization() ");
            // 模拟动态代理AOP
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                KHighnessExecutingLog.info("动态代理: Proxy.newProxyInstance()");
                method.invoke(bean, args);
                return null;
            });
        }
        return null;
    }

}
