package com.example.module4_backend.service.user;

import com.example.module4_backend.model.entity.User;
import com.example.module4_backend.model.entity.UserInfo;
import com.example.module4_backend.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
    List<User> findALl();
}
