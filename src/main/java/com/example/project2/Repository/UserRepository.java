package com.example.project2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.Entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserName(String userName);
    List<User> findByDeletedFalse();
    User findByIdAndDeletedFalse(int id);
    User findByPassword(String password);
}
