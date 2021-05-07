package top.parak.springcustom;

/**
 * @author KHighness
 * @since 2021-05-07
 * @apiNote bean的名称回调接口
 */

public interface BeanNameAware {

    void setBeanName(String name);

}
