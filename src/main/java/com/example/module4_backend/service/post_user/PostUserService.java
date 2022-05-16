package com.example.module4_backend.service.post_user;

import com.example.module4_backend.model.entity.PostUser;
import com.example.module4_backend.repository.IPostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostUserService implements IPostUserService{
    @Autowired
    private IPostUserRepository postUserRepository;
    @Override
    public Page<PostUser> findALl(Pageable pageable) {
        return postUserRepository.findAll(pageable);
    }

    @Override
    public Optional<PostUser> findById(Long id) {
        return postUserRepository.findById(id);
    }

    @Override
    public PostUser save(PostUser postUser) {
        return postUserRepository.save(postUser);
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<PostUser> findAll() {
        return postUserRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        postUserRepository.deletePost(id);
    }

    @Override
    public List<PostUser> showAllPostUserByUserInfoId(Long userInfoId) {
        return postUserRepository.showAllPostUserByUserInfoId(userInfoId);
    }

    @Override
    public List<PostUser> findAllPostInHome(Long userInfoId) {
        return null;
    }

    @Override
    public List<PostUser> findByStatusandUserInfoId(Long userInfoId, Long statusId) {
        return postUserRepository.findByStatusandUserInfoId(userInfoId, statusId);
    }

    @Override
    public List<PostUser> findAllPostFriend(Long id) {
        return postUserRepository.findAllPostFriend(id);
    }
}
