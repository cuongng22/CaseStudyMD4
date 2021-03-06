package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.ImagePostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagePostUserRepository extends JpaRepository<ImagePostUser, Long> {
    @Query(value = "select * from image_post_user where post_user_id = ?1", nativeQuery = true)
    ImagePostUser[] listImage(Long postUser);
}
