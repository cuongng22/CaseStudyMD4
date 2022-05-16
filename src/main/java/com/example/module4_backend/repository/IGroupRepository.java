package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IGroupRepository extends JpaRepository<Group, Long> {
    @Modifying
    @Query(value = "select group _id from (select * from group_members_groups join group ) where user_info_id= ?1", nativeQuery = true)
     List<Group> findAllGroupByUserId(Long userInfoId);
}
