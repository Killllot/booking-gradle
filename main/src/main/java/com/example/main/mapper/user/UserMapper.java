package com.example.main.mapper.user;

//import com.newBooking.DTO.User.createUserDTO;
//import com.newBooking.Data.Entity.UserEntity;

import com.example.main.dto.user.ValidatedUserDto;
import com.example.main.entity.UserEntity;
import com.example.main.models.User;

public class UserMapper {

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUserName(entity.getUsername());
        return model;
    }

    public static UserEntity fromDtoToEntity (ValidatedUserDto userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUserName());

        return user;
    }

}
