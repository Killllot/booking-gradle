package com.example.main.service.impl;

import com.example.main.entity.UserEntity;
import com.example.main.repository.RoleRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.Interface.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Value("${const.minimumUserNameLength}")
    private long minimumNameLength;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result);
        return result;
    }


    @Override
    public UserEntity getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        log.info("IN getUser - user: {} found by id: {}",user, id);
        if(user==null) {
            log.warn("IN getUser - no user found by id: {}", id);
            throw new RuntimeException("Пользователь с таким id не найден");
        }
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("IN deleteUser - user: found by id: {}",id);

    }
}
