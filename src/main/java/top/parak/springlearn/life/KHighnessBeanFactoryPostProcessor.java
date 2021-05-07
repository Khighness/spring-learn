package top.parak.springlearn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import top.parak.springlearn.common.KHighnessExecutingLog;

/**
 * @author KHighness
 * @since 2021-05-06
 */

@Component
public class KHighnessBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        KHighnessExecutingLog.info("预处理: BeanFactoryPostProcessor.postProcessBeanFactory()");
    }
}
