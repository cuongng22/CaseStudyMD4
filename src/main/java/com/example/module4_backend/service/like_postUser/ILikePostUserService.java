package com.example.module4_backend.service.like_postUser;

import com.example.module4_backend.model.entity.LikePostUser;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikePostUserService extends IGeneralService<LikePostUser> {
    Optional<LikePostUser> findLikePostUserByUserInfoIdAndPostUserId(Long userInfoId, Long postUserId);
    List<LikePostUser> totalLikeByPost(Long postUserId);
}
