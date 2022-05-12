package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCommentPostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @ManyToOne
    private CommentPostUser commentPostUser;

    @ManyToOne
    private UserInfo userInfo;

    public LikeCommentPostUser(boolean status, CommentPostUser commentPostUser, UserInfo userInfo) {
        this.status = status;
        this.commentPostUser = commentPostUser;
        this.userInfo = userInfo;
    }
}
