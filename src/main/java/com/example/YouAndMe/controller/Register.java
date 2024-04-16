package com.example.YouAndMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YouAndMe.entity.User;
import com.example.YouAndMe.payload.request.RegisterRequest;
import com.example.YouAndMe.payload.response.ErrorResponse;
import com.example.YouAndMe.payload.response.MessageResponse;
import com.example.YouAndMe.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/public")
public class Register {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder encoder;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Mã số sổ  đã được đăng ký!!!"));
        }
        if (userService.existsByUserName(registerRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Email đã được đăng ký!!!"));
        }

        // Create new user's user
        User user = new User(registerRequest.getUserName(), registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));
        // Add new user's user to database
        User newUser = userService.create(user);
        // Find ID user's user newest after add to database
        // Integer iduserAfterCreated = userService.findIdUserByUserName(user.getUserName());
        // // Set default role is "ROLE_USER"
        // roleService.setDefaultRole(iduserAfterCreated, 1);
        // // Add new patient
        // patientService.addPatient(
        //         registerRequest.getName(),
        //         registerRequest.getDateOfBirth(),
        //         registerRequest.getGender(),
        //         registerRequest.getGuardian(),
        //         registerRequest.getPhone(),
        //         registerRequest.getAddress(),
        //         registerRequest.getEmail(), iduserAfterCreated, false);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
    }
}
