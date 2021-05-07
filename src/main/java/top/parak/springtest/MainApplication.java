package top.parak.springtest;

import top.parak.springcustom.KHighnessApplicationContext;
import top.parak.springtest.config.AppConfig;
import top.parak.springtest.service.UserService;

/**
 * @author KHighness
 * @since 2021-05-06
 */

public class MainApplication {
    public static void main(String[] args) {
        KHighnessApplicationContext applicationContext = new KHighnessApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService.getName());
        System.out.println(userService.getOrderService());
    }
}
