package com.example.module4_backend.repository;

import com.example.module4_backend.model.dto.SearchByName;
import com.example.module4_backend.model.entity.GroupMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupMemberRepository extends JpaRepository<GroupMember,Long> {
    @Query(  value = "SELECT * FROM groupmembers WHERE group_id=?1",nativeQuery = true)
    Page<GroupMember> findAllByGroup1Id(Long group1Id, Pageable pageable);
    @Query(value = "SELECT g.id as groupmembersId , g.status  as status,g.group_id as groupId,g1.back_ground_url as bgrGroup, g1.group_name1 as groupName1,g.userinfo_id as userinfoId,ui.address as address,ui.avatar as avatar ,ui.back_ground as backGroundUser,ui.sex   as sex, ui.name as fullName,ui.user_id as userId FROM groupmembers g join group1 g1 on g1.id=g.group_id join user_info ui on g.userinfo_id =ui.id WHERE ui.full_name like ?1",nativeQuery = true)
    Page<SearchByName>findByNameUser (String fullname , Pageable pageable);
}