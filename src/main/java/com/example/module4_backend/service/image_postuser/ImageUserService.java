package com.example.module4_backend.service.image_postuser;

import com.example.module4_backend.model.entity.ImagePostUser;
import com.example.module4_backend.repository.IImagePostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImageUserService implements IImageUserService{
    @Autowired
    private IImagePostUserRepository iImagePostUserRepository;

    @Override
    public Page<ImagePostUser> findALl(Pageable pageable) {
        return iImagePostUserRepository.findAll(pageable);
    }

    @Override
    public Optional<ImagePostUser> findById(Long id) {
        return iImagePostUserRepository.findById(id);
    }

    @Override
    public ImagePostUser save(ImagePostUser imagePostUser) {
        return iImagePostUserRepository.save(imagePostUser);
    }

    @Override
    public void deleteById(Long id) {
        iImagePostUserRepository.deleteById(id);
    }

    @Override
    public List<ImagePostUser> findAll() {
        return iImagePostUserRepository.findAll();
    }

    @Override
    public ImagePostUser[] listImage(Long postUser) {
        return iImagePostUserRepository.listImage(postUser);
    }
}
