package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String sex;

    private String address;

    private String avatar;

    private String background;

    @OneToOne
    private User user;

    public UserInfo(String name, int age, String sex, String address, User user) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.user = user;
    }

    public UserInfo(String name, int age, String sex, String address, String avatar, User user) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.avatar = avatar;
        this.user = user;
    }

    public UserInfo(Long id, String name, int age, String sex, String address, User user) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.user = user;
    }
}




