package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.NotificationUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.notificationUser.INotificationUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationAddFriendController {
    @Autowired
    private INotificationUserService notificationUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationUser>> showAllNotification(@PathVariable Long userId) {
        UserInfo userInfo1 = userInfoService.findByUserId(userId).get();
        List<NotificationUser> list = notificationUserService.showALl(userInfo1.getId());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationUser> delete(@PathVariable Long id) {
        notificationUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/friend/{fromUserInfo}/{toUserInfo}")
    public ResponseEntity<NotificationUser> create(@PathVariable Long fromUserInfo, @PathVariable Long toUserInfo) {
        UserInfo userInfo1 = userInfoService.findById(fromUserInfo).get();
        UserInfo userInfo2 = userInfoService.findById(toUserInfo).get();
        NotificationUser notificationUser = new NotificationUser(
                userInfo1.getName() + " đã gửi lời mời kết bạn ",
                new Date(),
                userInfo1,userInfo2
        );
        notificationUserService.save(notificationUser);
        return new ResponseEntity<>(notificationUser, HttpStatus.OK);
    }
}
