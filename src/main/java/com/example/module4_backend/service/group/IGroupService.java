package com.example.module4_backend.service.group;

import com.example.module4_backend.model.entity.Group1;
import com.example.module4_backend.service.IGeneralService;

public interface IGroupService extends IGeneralService<Group1> {
    Iterable<Group1> findAllGroup();
}