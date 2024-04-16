package com.example.YouAndMe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.YouAndMe.entity.User;
import com.example.YouAndMe.service.UserService;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    @Autowired
    UserService accountService;

    @GetMapping("/account/search")
    public List<User> getAccounts(
            @Nullable @RequestParam Integer id,
            @Nullable @RequestParam String email,
            @Nullable @RequestParam String userName
    ) {
        List<User> accounts = accountService.find(id, email, userName);
        return accounts;
    }

    @PostMapping("/account/create")
    public User postMethodName(@RequestBody User newAccount) {
        //TODO: process POST request
        return accountService.create(newAccount);
    }

}
