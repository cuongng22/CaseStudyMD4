package com.example.module4_backend.service.status_postUser;

import com.example.module4_backend.model.entity.StatusPostUser;
import com.example.module4_backend.repository.IStatusPostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusPostUserService  implements IStatusPostUserService{
    @Autowired
    private IStatusPostUserRepository statusPostUserRepository;
    @Override
    public Page<StatusPostUser> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StatusPostUser> findById(Long id) {
        return statusPostUserRepository.findById(id);
    }

    @Override
    public StatusPostUser save(StatusPostUser statusPostUser) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<StatusPostUser> findAll() {
        return null;
    }
}
