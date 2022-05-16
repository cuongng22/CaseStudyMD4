package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IGroupMemberRepository extends JpaRepository<GroupMember, Long> {
    @Query(value = "select * from group_members where id in (select group_member_id from group_members_groups where groups_id = ?1)", nativeQuery = true)
    List<GroupMember> findByGroup(Long id);
    @Modifying
    @Query(value = "select group_member_id from (select * from group_members_groups join group_members) where user_info_id= ?1 and groups_id = ?2", nativeQuery = true)
    GroupMember findByUserInfoIdAndGroupId(Long userInfoId, Long groupId);


}
