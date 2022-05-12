package com.example.module4_backend.service.comment_postUser;

import com.example.module4_backend.model.entity.CommentPostUser;
import com.example.module4_backend.repository.ICommentPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentPostUserService implements ICommentPostUserService {
    @Autowired
    private ICommentPostRepository commentPostRepository;
    @Override
    public Page<CommentPostUser> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CommentPostUser> findById(Long id) {
        return commentPostRepository.findById(id);
    }

    @Override
    public CommentPostUser save(CommentPostUser commentPostUser) {
        return commentPostRepository.save(commentPostUser);
    }

    @Override
    public void deleteById(Long id) {
        commentPostRepository.deleteById(id);
    }

    @Override
    public List<CommentPostUser> findAll() {
        return commentPostRepository.findAll();
    }

    @Override
    public List<CommentPostUser> showAllByPost(Long id) {
        return commentPostRepository.showAllByPost(id);
    }
}
