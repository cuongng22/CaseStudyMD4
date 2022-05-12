package com.example.module4_backend.service.like_cmtPostUser;

import com.example.module4_backend.model.entity.LikeCommentPostUser;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikeCmtPostUserService extends IGeneralService<LikeCommentPostUser> {
    Optional<LikeCommentPostUser> findLikeCommentByUser(Long cmPostUserId, Long fromUserId);
    List<LikeCommentPostUser> listLikeComments(Long cmPostUserId);

}
