package com.example.module4_backend.service.role;

import com.example.module4_backend.model.entity.Role;
import com.example.module4_backend.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class RoleService  implements IRoleService{
    @Autowired
    private IRoleRepository repository;

    @Override
    public Page<Role> findALl(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }
}
