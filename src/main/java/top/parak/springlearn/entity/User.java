package top.parak.springlearn.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.parak.springlearn.common.KHighnessExecutingLog;

import java.security.KeyStore;

/**
 * @author KHighness
 * @since 2021-03-30
 */

@ToString
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User() {
        this.id = 1;
        this.name = "KHighness";
        this.age = 19;
    }
}
