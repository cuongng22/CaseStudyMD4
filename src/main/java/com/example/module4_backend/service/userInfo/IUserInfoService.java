package com.example.module4_backend.service.userInfo;

import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IUserInfoService extends IGeneralService<UserInfo> {
    UserInfo findByEmail(String email);
    UserInfo findByPhoneNumber(String email);
    List<UserInfo> findALl();
    Optional<UserInfo> findByUserId(Long userId);
}
