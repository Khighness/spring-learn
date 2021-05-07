package top.parak.springcustom;

/**
 * @author KHighness
 * @since 2021-05-07
 * @apiNote bean定义信息
 */

public class BeanDefinition {

    /**
     * 类型
     */
    private Class clazz;

    /**
     * 作用域
     */
    private String scope;

    public BeanDefinition() {
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "BeanDefinition[" +
                "clazz=" + clazz +
                ", scope='" + scope + '\'' +
                ']';
    }
}
