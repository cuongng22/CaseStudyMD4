package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateCreater;

    @ManyToOne
    private StatusPostUser status;

    @ManyToOne
    private UserInfo userInfo;

    public PostUser(String content, Date dateCreater, StatusPostUser status, UserInfo userInfo) {
        this.content = content;
        this.dateCreater = dateCreater;
        this.status = status;
        this.userInfo = userInfo;
    }
}
