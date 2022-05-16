package com.example.module4_backend.service.group_member;

import com.example.module4_backend.model.entity.GroupMember;
import com.example.module4_backend.repository.IGroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupMemberSerivce implements IGroupMemberSerivce{
    @Autowired
    private IGroupMemberRepository groupMemberRepository;
    @Override
    public Page<GroupMember> findALl(Pageable pageable) {
        return groupMemberRepository.findAll(pageable);
    }

    @Override
    public Optional<GroupMember> findById(Long id) {
        return groupMemberRepository.findById(id);
    }

    @Override
    public GroupMember save(GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    @Override
    public void deleteById(Long id) {
        groupMemberRepository.deleteById(id);
    }

    @Override
    public List<GroupMember> findAll() {
        return groupMemberRepository.findAll();
    }

    @Override
    public List<GroupMember> findByGroup(Long id) {
        return groupMemberRepository.findByGroup(id);
    }

    @Override
    public GroupMember findByUserInfoIdAndGroupId(Long userInfoId, Long groupId) {
        return groupMemberRepository.findByUserInfoIdAndGroupId(userInfoId, groupId);
    }
}
