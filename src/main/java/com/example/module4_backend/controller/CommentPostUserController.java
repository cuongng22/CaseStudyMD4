package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.CommentPostUser;
import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.comment_postUser.ICommentPostUserService;
import com.example.module4_backend.service.post_user.IPostUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin("*")
@RequestMapping("/commentUser")
@RestController
public class CommentPostUserController {
    @Autowired
    private ICommentPostUserService commentPostUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    IPostUserService postUserService;

    @PostMapping("/{userId}/{postUserId}")
    public ResponseEntity<CommentPostUser> createComment(@PathVariable Long userId, @PathVariable Long postUserId, @RequestBody CommentPostUser commentPostUser) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        PostUser postUser = postUserService.findById(postUserId).get();
        Date date = new Date();
        CommentPostUser newCommentPostUser = new CommentPostUser(
                commentPostUser.getContent(),
                date,
                postUser,
                userInfo);
                commentPostUserService.save(newCommentPostUser);
        return new ResponseEntity<>(newCommentPostUser, HttpStatus.OK);
    }

}
