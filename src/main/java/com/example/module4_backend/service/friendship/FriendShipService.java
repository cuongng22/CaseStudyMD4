package com.example.module4_backend.service.friendship;

import com.example.module4_backend.model.entity.FriendShip;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.repository.IFriendShipRepo;
import com.example.module4_backend.repository.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendShipService implements IFriendShipService{
    @Autowired
    private IFriendShipRepo friendShipRepo;

    @Autowired
    private IUserInfoRepository iUserInfoRepository;

    @Override
    public Page<FriendShip> findALl(Pageable pageable) {
        return friendShipRepo.findAll(pageable) ;
    }

    @Override
    public Optional<FriendShip> findById(Long id) {
        return friendShipRepo.findById(id);
    }

    @Override
    public FriendShip save(FriendShip friendShip) {
        return friendShipRepo.save(friendShip);
    }

    @Override
    public void deleteById(Long id) {
        friendShipRepo.deleteById(id);
    }

    @Override
    public List<FriendShip> findAll() {
        return friendShipRepo.findAll();
    }

    @Override
    public List<Long> showListFriend(Long userId, Long statusId) {
        return friendShipRepo.showListFriend(userId,statusId);
    }

    @Override
    public void deleteFriend(Long userInfoId1, Long userInfoId2) {
        friendShipRepo.deleteFriend(userInfoId1,userInfoId2 );
    }

    @Override
    public List<UserInfo> showAllNotFriend(Long id, Long status) {
        List<UserInfo> userInfoList = iUserInfoRepository.findAll();
        List<Long> list= friendShipRepo.showListFriend(id, status);
        for (int j = 0 ; j < userInfoList.size(); j++) {
            for (int i = 0; i < list.size(); i ++) {
                if (list.get(i) == userInfoList.get(j).getId() ) {
                    userInfoList.remove(j);
                }
            }
        }
        for (int j = 0 ; j < userInfoList.size(); j++) {
            if (userInfoList.get(j).getId() == id) {
                userInfoList.remove(j);
            }
        }
        return userInfoList;
    }
}
