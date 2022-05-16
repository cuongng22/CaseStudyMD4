package com.example.module4_backend.service.group;

import com.example.module4_backend.model.entity.Group;
import com.example.module4_backend.service.IGeneralService;

import java.util.List;

public interface IGroupService extends IGeneralService<Group> {
    public List<Group> findAllGroupByUserId(Long userId);

}
