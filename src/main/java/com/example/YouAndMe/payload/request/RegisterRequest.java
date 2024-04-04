package com.example.YouAndMe.payload.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String userName;
    String email;
    String password;
}
