package com.example.module4_backend.controller;

import com.example.module4_backend.model.dto.PostUserForm;
import com.example.module4_backend.model.dto.PostUserFrontEnd;
import com.example.module4_backend.model.entity.ImagePostUser;
import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.comment_postUser.ICommentPostUserService;
import com.example.module4_backend.service.image_postuser.IImageUserService;
import com.example.module4_backend.service.like_cmtPostUser.ILikeCmtPostUserService;
import com.example.module4_backend.service.like_postUser.ILikePostUserService;
import com.example.module4_backend.service.post_user.IPostUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post_user")
@CrossOrigin("*")
public class PostUserController {

    @Value("${file-upload}")
    private String uploadPath;

    @Autowired
    private IPostUserService postUserService;

    @Autowired
    private ICommentPostUserService commentPostUserService;

    @Autowired
    private ILikePostUserService likePostUserService;

    @Autowired
    private ILikeCmtPostUserService likeCmtPostUserService;


    @Autowired
    private IImageUserService iImageUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostUserFrontEnd>> showAllPostUser(@PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        List<PostUser> postUsers = postUserService.showAllPostUserByUserInfoId(userInfo.getId());
        List<PostUserFrontEnd> postUserFrontEnds  = new ArrayList<>();
        for (int  i = 0 ; i< postUsers.size(); i++) {
            postUserFrontEnds.add(new PostUserFrontEnd(postUsers.get(i).getId(),
                    postUsers.get(i).getContent(),
                    postUsers.get(i).getDateCreater(),
                    iImageUserService.listImage(postUsers.get(i).getId()),
                    postUsers.get(i).getStatus(),
                    userInfo,
                    likePostUserService.totalLikeByPost(postUsers.get(i).getId()).size(),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()).size(),
                    likeCmtPostUserService.listLikeComments(postUsers.get(i).getId()).size()
                    ));
        }
        return new ResponseEntity<>(postUserFrontEnds, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostUser> createNewPost(@ModelAttribute PostUserForm postUserForm, @PathVariable Long userId) {
        MultipartFile[] multipartFiles = postUserForm.getImage();
        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);
        List<String> images = new ArrayList<>();

        PostUser postUser = new PostUser(
                postUserForm.getContent(),
                new Date(),
                postUserForm.getStatus(),
                userInfo.get()
        );
        postUserService.save(postUser);
        if (multipartFiles != null) {
            for (int  i = 0 ; i < multipartFiles.length; i++) {
                images.add(multipartFiles[i].getOriginalFilename());
            }
            for (int i = 0; i <images.size(); i++) {
                iImageUserService.save(new ImagePostUser(images.get(i), postUser));
                try {
                    FileCopyUtils.copy(multipartFiles[i].getBytes(), new File(uploadPath + images.get(i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ResponseEntity<>(postUser , HttpStatus.OK);
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<List<PostUserFrontEnd>> findByNameAndStatus(@PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        List<PostUser> postUsers = postUserService.findByStatusandUserInfoId(userInfo.getId(), 1L);
        List<PostUserFrontEnd> postUserFrontEnds  = new ArrayList<>();
        for (int  i = 0 ; i< postUsers.size(); i++) {
            postUserFrontEnds.add(new PostUserFrontEnd(postUsers.get(i).getId(),
                    postUsers.get(i).getContent(),
                    postUsers.get(i).getDateCreater(),
                    iImageUserService.listImage(postUsers.get(i).getId()),
                    postUsers.get(i).getStatus(),
                    userInfo,
                    likePostUserService.totalLikeByPost(postUsers.get(i).getId()).size(),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()).size(),
                    likeCmtPostUserService.listLikeComments(postUsers.get(i).getId()).size()
            ));
        }
        return new ResponseEntity<>(postUserFrontEnds, HttpStatus.OK);
    }

    @GetMapping("/friends/{id}")
    public ResponseEntity<List<PostUserFrontEnd>> showALlByfr(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.findByUserId(id).get();
        List<PostUser> postUsers = postUserService.findAllPostFriend(userInfo.getId());
        List<PostUserFrontEnd> postUserFrontEnds  = new ArrayList<>();
        for (int  i = 0 ; i< postUsers.size(); i++) {
            postUserFrontEnds.add(new PostUserFrontEnd(postUsers.get(i).getId(),
                    postUsers.get(i).getContent(),
                    postUsers.get(i).getDateCreater(),
                    iImageUserService.listImage(postUsers.get(i).getId()),
                    postUsers.get(i).getStatus(),
                    postUsers.get(i).getUserInfo(),
                    likePostUserService.totalLikeByPost(postUsers.get(i).getId()).size(),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()),
                    commentPostUserService.showAllByPost(postUsers.get(i).getId()).size(),
                    likeCmtPostUserService.listLikeComments(postUsers.get(i).getId()).size()
            ));
        }
        return new ResponseEntity<>(postUserFrontEnds, HttpStatus.OK);
    }

}
