package com.example.module4_backend.service.friendship;

import com.example.module4_backend.model.entity.FriendShip;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;

public interface IFriendShipService extends IGeneralService<FriendShip> {
    List<Long> showListFriend(Long userId, Long statusId);

    void deleteFriend(Long userInfoId1, Long userInfoId2);

    List<UserInfo> showAllNotFriend(Long id, Long status);
}
