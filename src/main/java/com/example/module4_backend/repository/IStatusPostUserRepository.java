package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.StatusPostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusPostUserRepository extends JpaRepository<StatusPostUser, Long> {
}
