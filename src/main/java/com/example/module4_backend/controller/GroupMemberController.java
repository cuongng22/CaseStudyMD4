package com.example.module4_backend.controller;

import com.example.module4_backend.model.entity.GroupMember;
import com.example.module4_backend.service.group_member.IGroupMemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group_member")
@RestController
@CrossOrigin("*")
public class GroupMemberController {
    @Autowired
    private IGroupMemberSerivce groupMemberSerivce;
    @GetMapping("/member/{groupId}")
    public ResponseEntity<List<GroupMember>> findMemberOnGroup(@PathVariable Long groupId) {
        List<GroupMember> groupMembers = groupMemberSerivce.findByGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<GroupMember> delte(@PathVariable Long memberId) {
        groupMemberSerivce.deleteById(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GroupMember> add( @ModelAttribute GroupMember groupMember) {
        groupMemberSerivce.save(groupMember);
        groupMember.setStatus(2);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
