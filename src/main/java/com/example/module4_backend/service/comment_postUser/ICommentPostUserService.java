package com.example.module4_backend.service.comment_postUser;

import com.example.module4_backend.model.entity.CommentPostUser;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;

public interface ICommentPostUserService extends IGeneralService<CommentPostUser> {
    List<CommentPostUser> showAllByPost(Long id);
}
