package com.example.module4_backend.service.group;

import com.example.module4_backend.model.entity.Group;
import com.example.module4_backend.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GroupService implements IGroupService{
    @Autowired
    private IGroupRepository groupRepository;
    @Override
    public Page<Group> findALl(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> findAllGroupByUserId(Long userId) {
        return groupRepository.findAllGroupByUserId(userId);
    }
}
