package com.example.module4_backend.service.post_user;

import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;

public interface IPostUserService extends IGeneralService<PostUser> {
    void deletePost(Long id);
    List<PostUser> showAllPostUserByUserInfoId(Long userInfoId);
    List<PostUser> findAllPostInHome(Long userInfoId);
    List<PostUser> findByStatusandUserInfoId(Long userInfoId, Long statusId);
    List<PostUser> findAllPostFriend(Long id);
}
