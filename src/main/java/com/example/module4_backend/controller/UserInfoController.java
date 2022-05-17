package com.example.module4_backend.controller;

import com.example.module4_backend.model.dto.AvatarForm;
import com.example.module4_backend.model.dto.BackgroundForm;
import com.example.module4_backend.model.entity.ImagePostUser;
import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.model.entity.User;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.image_postuser.IImageUserService;
import com.example.module4_backend.service.post_user.IPostUserService;
import com.example.module4_backend.service.status_postUser.IStatusPostUserService;
import com.example.module4_backend.service.user.IUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private IPostUserService postUserService;
    @Autowired
    private IImageUserService iImageUserService;
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStatusPostUserService statusPostUserService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Page<UserInfo>> showAll(@PageableDefault(value = 5) Pageable pageable) {
        Page<UserInfo> userInfos = userInfoService.findALl(pageable);
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserInfo> createUserInfo(@ModelAttribute UserInfo userInfo) {
        if (userInfo.getAvatar() == null) {
            userInfo.setAvatar("facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg");
        }
        if (userInfo.getBackground() == null) {
            userInfo.setBackground("facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg");
        }
        userInfoService.save(userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

    @PostMapping("/setAvatar/{userInfoId}")
    public ResponseEntity<UserInfo>  updateAvt(@PathVariable Long userInfoId ,@ModelAttribute AvatarForm avatarForm) {
        MultipartFile multipartFile = avatarForm.getAvatar();
        String avatar = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + avatar));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userInfoService.findById(userInfoId).get();
        userInfo.setAvatar(avatar);
        PostUser postUser= new PostUser(
                userInfo.getName() + " đã thay đổi ảnh đại diện",
                new Date(),
                statusPostUserService.findById(2L).get(),
                userInfo
        );
        postUserService.save(postUser);
        iImageUserService.save(new ImagePostUser(avatar,postUser ));
        userInfoService.save(userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/setBackground/{userInfoId}")
    public ResponseEntity<UserInfo>  updateBackground(@PathVariable Long userInfoId , @ModelAttribute BackgroundForm backgroundForm) {
        MultipartFile multipartFile = backgroundForm.getBackground();
        String bgr = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + bgr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userInfoService.findById(userInfoId).get();
        userInfo.setBackground(bgr);
        userInfoService.save(userInfo);
        PostUser postUser= new PostUser(
                userInfo.getName() + " đã thay đổi ảnh bìa",
                new Date(),
                statusPostUserService.findById(2L).get(),
                userInfo
        );
        postUserService.save(postUser);
        iImageUserService.save(new ImagePostUser(bgr,postUser ));
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/findOne/{userId}")
    public ResponseEntity<UserInfo> findByUserId(@PathVariable Long userId) {
        UserInfo userInfo = null;
        List<UserInfo> userInfos = userInfoService.findALl();
        for (UserInfo userInfo123 : userInfos) {
            if (userInfo123.getUser().getId() == userId) {
                userInfo = userInfo123;
            }
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/edit/{userId}")
    public ResponseEntity<UserInfo> edit(@PathVariable Long userId, @ModelAttribute UserInfo userInfo) {
        User user = userService.findById(userId).get();
        UserInfo userInfo1 = userInfoService.findByUserId(userId).get();
        UserInfo newUserInfo = new UserInfo(userInfo1.getId(), userInfo.getName(), userInfo.getAge(), userInfo.getSex(), userInfo.getAddress(), user );
        newUserInfo.setAvatar(userInfo1.getAvatar());
        newUserInfo.setBackground(userInfo1.getBackground());
        userInfoService.save(newUserInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
