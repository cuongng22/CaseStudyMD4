package com.example.module4_backend.service.userInfo;

import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.repository.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoRepository userInfoRepository;


    @Override
    public Page<UserInfo> findALl(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public void deleteById(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    @Override
    public UserInfo findByPhoneNumber(String email) {
        return userInfoRepository.findByPhoneNumber(email);
    }

    @Override
    public List<UserInfo> findALl() {
        return userInfoRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId);
    }
}
