    package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateCreated;

    @ManyToOne
    private PostUser postUser;

    @ManyToOne
    private UserInfo userInfo;

    public CommentPostUser(String content, Date dateCreated, PostUser postUser, UserInfo userInfo) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.postUser = postUser;
        this.userInfo = userInfo;
    }

    public CommentPostUser(String content, Date dateCreated) {
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public CommentPostUser(String content) {
        this.content = content;
    }
}
