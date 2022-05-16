package com.example.module4_backend.service.group_member;

import com.example.module4_backend.model.entity.GroupMember;
import com.example.module4_backend.service.IGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGroupMemberSerivce extends IGeneralService<GroupMember> {
    List<GroupMember> findByGroup(Long id);
    GroupMember findByUserInfoIdAndGroupId(Long userInfoId, Long groupId);
}
