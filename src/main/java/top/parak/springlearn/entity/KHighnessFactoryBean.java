package top.parak.springlearn.entity;

import org.springframework.beans.factory.FactoryBean;
import top.parak.springlearn.entity.User;

/**
 * @author KHighness
 * @since 2021-03-30
 */

public class KHighnessFactoryBean implements FactoryBean {
    public Object getObject() throws Exception {
        User user = new User();
        return user;
    }

    public Class<?> getObjectType() {
        return User.class;
    }
}
