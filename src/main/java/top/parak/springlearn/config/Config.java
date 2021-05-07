package top.parak.springlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.parak.springlearn.service.UserService;

/**
 * @author KHighness
 * @since 2021-05-06
 */

@Configuration
@ComponentScan("top.parak.springlearn")
public class Config {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public UserService userService() {
        UserService userService = new UserService();
        userService.setName("KHighness");
        return userService;
    }
}
