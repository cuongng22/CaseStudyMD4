package com.example.module4_backend.service.image_postuser;

import com.example.module4_backend.model.entity.ImagePostUser;
import com.example.module4_backend.service.IGeneralService;

public interface IImageUserService extends IGeneralService<ImagePostUser> {
    ImagePostUser[] listImage(Long postUser);
}
