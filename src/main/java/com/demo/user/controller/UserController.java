package com.demo.user.controller;

import com.demo.user.entity.User;
import com.demo.user.service.UserService;
import com.demo.user.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public UserResponse saveUser(@RequestBody User user) {
        log.info("Inside UserController::saveUser");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        log.info("Inside UserController::getUser");
        return userService.getUserResponse(id);
    }
}
