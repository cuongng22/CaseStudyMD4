package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_members")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Group> groups;

    @ManyToOne
    private UserInfo userInfo;

    private int status;

    public GroupMember(List<Group> groups, UserInfo userInfo) {
        this.groups = groups;
        this.userInfo = userInfo;
    }

    public GroupMember(List<Group> groups, UserInfo userInfo, int status) {
        this.groups = groups;
        this.userInfo = userInfo;
        this.status = status;
    }

    public GroupMember(UserInfo userInfo, int status) {
        this.userInfo = userInfo;
        this.status = status;
    }
}
