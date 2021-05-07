package top.parak.springlearn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.parak.springlearn.common.KHighnessExecutingLog;

/**
 * @author KHighness
 * @since 2021-05-06
 */

@Component
public class KHighnessBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        KHighnessExecutingLog.info("初始化前: BeanPostProcessor.postProcessBeforeInitialization()");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        KHighnessExecutingLog.info("初始化后: BeanPostProcessor.postProcessAfterInitialization()");
        return null;
    }
}
