package com.example.module4_backend.model.dto;

import com.example.module4_backend.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserFrontEnd {
    private Long id;

    private String content;

    private Date dateCreater;

    private ImagePostUser[] image;

    @ManyToOne
    private StatusPostUser statusPostUser;

    private UserInfo userInfo;

    private Integer totalLike;

    private List<CommentPostUser> commentPostUsers;

    private Integer totalComment;

    private List<Integer> totalLikeComment;

    public PostUserFrontEnd(String content, Date dateCreater, ImagePostUser[] image, StatusPostUser statusPostUser, UserInfo userInfo, Integer totalLike, List<CommentPostUser> commentPostUsers, Integer totalComment, List<Integer> totalLikeComment) {
        this.content = content;
        this.dateCreater = dateCreater;
        this.image = image;
        this.statusPostUser = statusPostUser;
        this.userInfo = userInfo;
        this.totalLike = totalLike;
        this.commentPostUsers = commentPostUsers;
        this.totalComment = totalComment;
        this.totalLikeComment = totalLikeComment;
    }
}
