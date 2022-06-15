package com.example.main.service.Interface;

import com.example.main.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAll();

    UserEntity getUser(Long id);

    void deleteUser(Long id);
}
