package com.example.YouAndMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.YouAndMe.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByIdOrEmailOrUserName(Integer id, String email, String userName);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    List<User> findByEmail(String email);

    List<User> findByUserName(String userName);
 
}