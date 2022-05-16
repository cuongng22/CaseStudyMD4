package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.FriendShip;
import com.example.module4_backend.model.entity.NotificationUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.friendship.IFriendShipService;
import com.example.module4_backend.service.notificationUser.INotificationUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/friendship")
@RestController
public class FriendShipController {
    @Autowired
    private INotificationUserService notificationUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IFriendShipService friendShipService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserInfo>> showAllFriend(@PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        List<Long> list = friendShipService.showListFriend(userInfo.getId(), 1L) ;
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0 ; i < list.size(); i++) {
            userInfoList.add(userInfoService.findById(list.get(i)).get());
        }
        return new ResponseEntity<>(userInfoList, HttpStatus.OK);
    }
    @PostMapping("/{fromUserId}/{toUserId}")
    public ResponseEntity<FriendShip> createNewFriendShip(@PathVariable Long fromUserId, @PathVariable Long toUserId) {
        UserInfo userInfo1= userInfoService.findByUserId(fromUserId).get();
        UserInfo userInfo2= userInfoService.findByUserId(toUserId).get();
        FriendShip friendShip1 = new FriendShip(userInfo1, userInfo2, 1);
        FriendShip friendShip2 = new FriendShip(userInfo2, userInfo1, 1);
        friendShipService.save(friendShip1);
        friendShipService.save(friendShip2);
        NotificationUser notificationUser = new NotificationUser(
                userInfo1.getName() + " đã chấp nhận lời mời kết bạn của bạn",
                new Date(),
                userInfo1,userInfo2
        );
        notificationUserService.save(notificationUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{fromUserId}/{toUserId}")
    public ResponseEntity<FriendShip> deleteFriendShip(@PathVariable Long fromUserId, @PathVariable Long toUserId) {
        UserInfo userInfo1= userInfoService.findByUserId(fromUserId).get();
        UserInfo userInfo2= userInfoService.findByUserId(toUserId).get();
        friendShipService.deleteFriend(userInfo1.getId(), userInfo2.getId());
        friendShipService.deleteFriend( userInfo2.getId(),userInfo1.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/notFr/{userId}")
    public ResponseEntity<List<UserInfo>> showNotFriend(@PathVariable Long userId) {
        UserInfo info = userInfoService.findByUserId(userId).get();
        List<UserInfo> list = friendShipService.showAllNotFriend(info.getId(), 1L);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
