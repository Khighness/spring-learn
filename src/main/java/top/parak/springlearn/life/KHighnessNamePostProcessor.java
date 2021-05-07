package top.parak.springlearn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.parak.springlearn.annotation.KHighnessName;

import java.lang.reflect.Field;

/**
 * @author KHighness
 * @since 2021-04-01
 */

@Component
public class KHighnessNamePostProcessor implements BeanPostProcessor {

    // Bean初始化前被调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=>>> bean初始化前 <<<=");
        Class<?> clazz = bean.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(KHighnessName.class)) {
                KHighnessName fieldAnnotation = field.getAnnotation(KHighnessName.class);
                String value = fieldAnnotation.value();
                field.setAccessible(true);
                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                System.out.println(value);
            }
        }
        return bean;
    }

    /// Bean初始化后被调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=>>> bean初始化后 <<<=");
        System.out.println(String.format("[bean=%s, beanName%s]", bean, beanName));
        return bean;
    }
}
