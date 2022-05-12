package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "select * from user_info where email = ?1", nativeQuery = true)
    UserInfo findByEmail(String email);

    @Query(value="select  * from user_info where user_id = ?1", nativeQuery = true)
    Optional<UserInfo> findByUserId(Long userId);

    @Query(value = "select * from user_info where phoneNumber = ?1", nativeQuery = true)
    UserInfo findByPhoneNumber(String email);
}
