package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.Group1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends JpaRepository<Group1,Long> {
}