package com.example.module4_backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups_")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    private String background;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, String avatar, String background) {
        this.name = name;
        this.avatar = avatar;
        this.background = background;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
