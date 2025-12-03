package com.example.springcrudmid.controller;

import com.example.springcrudmid.model.UserModel;
import com.example.springcrudmid.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel user) {
        return userService.registerUser(user);
    }
}