package com.example.module4_backend.repository;

import com.example.module4_backend.model.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IPostUserRepository extends JpaRepository<PostUser, Long> {
    @Modifying
    @Query(value = "call deletePost(?1)", nativeQuery = true)
    void deletePost(Long id);

    @Query(value = "select * from post_user where user_info_id = ?1 order by id desc", nativeQuery = true)
    List<PostUser> showAllPostUserByUserInfoId(Long userInfoId);

    @Query(value = "select * from post_user where user_info_id = ?1 and status_id = ?2 order by date_creater desc", nativeQuery = true)
    List<PostUser> findByStatusandUserInfoId(Long userInfoId, Long statusId);

    @Query(value = "select * from post_user where post_user.user_info_id in (select from_user_info_id from friend_ship where to_user_info_id = ?1)order by date_creater desc ", nativeQuery = true)
    List<PostUser> findAllPostFriend(Long id);
}
