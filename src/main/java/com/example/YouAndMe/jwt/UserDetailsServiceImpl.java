package com.example.YouAndMe.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.YouAndMe.entity.User;
import com.example.YouAndMe.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        List<User> users = userRepository.findByUserName(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        
        return UserDetailsImpl.build(users.get(0));

    }

}
