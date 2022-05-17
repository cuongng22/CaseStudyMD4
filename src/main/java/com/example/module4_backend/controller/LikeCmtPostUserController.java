package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.CommentPostUser;
import com.example.module4_backend.model.entity.LikeCommentPostUser;
import com.example.module4_backend.model.entity.NotificationUser;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.comment_postUser.ICommentPostUserService;
import com.example.module4_backend.service.like_cmtPostUser.ILikeCmtPostUserService;
import com.example.module4_backend.service.notificationUser.INotificationUserService;
import com.example.module4_backend.service.post_user.IPostUserService;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likeCommentUser")
public class LikeCmtPostUserController {
    @Autowired
    private ILikeCmtPostUserService likeCmtPostUserService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private INotificationUserService notificationUserService;

    @Autowired
    private IPostUserService postUserService;

    @Autowired
    private ICommentPostUserService commentPostUserService;

    @PostMapping("/{userId}/{commentId}")
    public ResponseEntity<LikeCommentPostUser> createLike(@PathVariable Long userId, @PathVariable Long commentId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        CommentPostUser commentPostUser = commentPostUserService.findById(commentId).get();
        Optional<LikeCommentPostUser> likeCommentPostUser = likeCmtPostUserService.findLikeCommentByUser(commentId, userInfo.getId());
        if (!likeCommentPostUser.isPresent()){
            NotificationUser notificationUser = new NotificationUser(
                    userInfo.getName() + " đã thich bình luận của bạn ",
                    new Date(),
                    userInfo,commentPostUser.getUserInfo()
            );
            notificationUserService.save(notificationUser);
            likeCmtPostUserService.save(new LikeCommentPostUser(true,  commentPostUser, userInfo));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            likeCmtPostUserService.deleteById(likeCommentPostUser.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Integer> showTotalLike(@PathVariable Long commentId) {
        return new ResponseEntity<>(likeCmtPostUserService.listLikeComments(commentId).size(), HttpStatus.OK);
    }
}
