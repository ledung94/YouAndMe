package com.example.YouAndMe.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YouAndMe.jwt.JwtUtils;
import com.example.YouAndMe.jwt.UserDetailsImpl;
import com.example.YouAndMe.payload.request.LoginRequest;
import com.example.YouAndMe.payload.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("api/public")
public class Login {
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        JwtUtils jwtUtils;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
                System.out.println(loginRequest);
                System.out.println(loginRequest.getUserName());
                System.out.println(loginRequest.getPassword());
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                                                loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                System.out.println("HERE =>>>>>>");
                System.out.println(jwt);
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                // List<String> roles = userDetails.getAuthorities().stream()
                //                 .map(item -> item.getAuthority())
                //                 .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                                userDetails.getId(),
                                userDetails.getUsername(),
                                userDetails.getEmail(),
                                null));
        }
}
