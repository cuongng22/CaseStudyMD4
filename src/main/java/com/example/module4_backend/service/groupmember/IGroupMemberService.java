package com.example.module4_backend.service.groupmember;

import com.example.module4_backend.model.entity.GroupMember;
import com.example.module4_backend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGroupMemberService extends IGeneralService<GroupMember> {

    Page<GroupMember> findAllByGroup1Id(Long group1Id, Pageable pageable);
//    Page<SearchByName>findByNameUser (String fullName , Pageable pageable);
}