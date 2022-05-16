package com.example.module4_backend.controller;

import com.example.module4_backend.model.dto.AvatarForm;
import com.example.module4_backend.model.dto.BackgroundForm;
import com.example.module4_backend.model.entity.Group;
import com.example.module4_backend.model.entity.GroupMember;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.repository.IUserInfoRepository;
import com.example.module4_backend.service.group.IGroupService;
import com.example.module4_backend.service.group_member.IGroupMemberSerivce;
import com.example.module4_backend.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/groups")
@RestController
public class GroupController {

    @Value("${file-upload}")
    private String uploadPath;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupMemberSerivce groupMemberSerivce;

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<List<Group>> showAllGroup() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/in/{userId}")
    public ResponseEntity<List<Group>> findByUserId(@PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        List<Group> groups = groupService.findAllGroupByUserId(userInfo.getId());
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Group> createNewGr(@ModelAttribute Group group, @PathVariable Long userId) {
        if (group.getAvatar() == null) {
            group.setAvatar("facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg");
        }
        if (group.getBackground() == null) {
            group.setBackground("facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg");
        }
        groupService.save(group);
        UserInfo userInfo = userInfoService.findByUserId(userId).get();
        List<Group> groups= new ArrayList<>();
        groups.add(group);
        GroupMember groupMember = new GroupMember(groups, userInfo, 1);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Group> editGroup(@PathVariable Long id, @ModelAttribute Group group) {
        Group group1 = groupService.findById(id).get();
        Group newGroup = new Group(id, group.getName());
        newGroup.setAvatar(group1.getAvatar());
        newGroup.setBackground(group1.getBackground());
        groupService.save(newGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Group> delete(@PathVariable Long id) {
        groupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/avt/{id}")
    public ResponseEntity<Group> editAvt(@PathVariable Long id, @ModelAttribute AvatarForm avatarForm) {
        Group group = groupService.findById(id).get();
        group.setAvatar(avatarForm.getAvatar().getOriginalFilename());
        try {
            FileCopyUtils.copy(avatarForm.getAvatar().getBytes(), new File(uploadPath + avatarForm.getAvatar().getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        groupService.save(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bgr/{id}")
    public ResponseEntity<Group> editBgr(@PathVariable Long id, @ModelAttribute BackgroundForm backgroundForm) {
        Group group = groupService.findById(id).get();
        group.setBackground(backgroundForm.getBackground().getOriginalFilename());
        try {
            FileCopyUtils.copy(backgroundForm.getBackground().getBytes(), new File(uploadPath + backgroundForm.getBackground().getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        groupService.save(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
