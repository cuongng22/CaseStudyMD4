package com.example.module4_backend.service.group;

import com.example.module4_backend.model.entity.Group1;
import com.example.module4_backend.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService {
    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public Iterable<Group1> findAllGroup(){
        return groupRepository.findAll();
    }

    @Override
    public Page<Group1> findALl(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Optional<Group1> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group1 save(Group1 group1) {
        return groupRepository.save(group1);
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group1> findAll() {
        return null;
    }
}