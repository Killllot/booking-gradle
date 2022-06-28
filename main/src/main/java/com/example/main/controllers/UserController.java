package com.example.main.controllers;

import com.example.main.mapper.user.UserMapper;
import com.example.main.service.Interface.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;


    @GetMapping("/getAll")
    public ResponseEntity getAll () {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping
    public ResponseEntity getOneUser (@NotNull @RequestParam Long id) {

        return ResponseEntity.ok(UserMapper.toModel(userService.getUser(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@NotNull @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete");
    }
}
