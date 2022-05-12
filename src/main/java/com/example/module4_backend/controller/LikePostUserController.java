package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.LikePostUser;
import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.like_postUser.ILikePostUserService;
import com.example.module4_backend.service.post_user.IPostUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/like_postUser")
public class LikePostUserController {
    @Autowired
    private ILikePostUserService likePostUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IPostUserService postUserService;

    @GetMapping("/{postUserId}")
    public ResponseEntity<Integer> totalLike(@PathVariable Long postUserId) {
        List<LikePostUser> likePostUsers = likePostUserService.totalLikeByPost(postUserId);
        Integer totalLike = likePostUsers.size();
        return new ResponseEntity<>(totalLike, HttpStatus.OK);
    }
    @PostMapping("/{userId}/{postUser_id}")
    public ResponseEntity<LikePostUser> createNewLike(@PathVariable Long postUser_id, @PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        PostUser postUser = postUserService.findById(postUser_id).get();
        Optional<LikePostUser> likePostUser = likePostUserService.findLikePostUserByUserInfoIdAndPostUserId(userInfo.getId(),postUser_id);
        if (!likePostUser.isPresent()) {
            LikePostUser newLikePostUser = new LikePostUser(true, postUser, userInfo);
            likePostUserService.save(newLikePostUser);
            return new ResponseEntity<>(newLikePostUser,HttpStatus.NOT_FOUND);
        } else {
            likePostUserService.deleteById(likePostUser.get().getId());
            return new ResponseEntity<>(likePostUser.get(),HttpStatus.OK);
        }
    }
}
