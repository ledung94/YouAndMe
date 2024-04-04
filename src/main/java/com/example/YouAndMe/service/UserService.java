package com.example.YouAndMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.YouAndMe.entity.User;
import com.example.YouAndMe.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User account) {
        return userRepository.save(account);
    }

    public List<User> find(Integer id, String email, String userName) {
        return userRepository.findUserByIdOrEmailOrUserName(id, email, userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
