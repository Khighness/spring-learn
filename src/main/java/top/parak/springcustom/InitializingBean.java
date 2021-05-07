package top.parak.springcustom;

/**
 * @author KHighness
 * @since 2021-05-07
 * @apiNote bean的初始化回调接口
 */

public interface InitializingBean {

    void afterPropertiesSet();

}
