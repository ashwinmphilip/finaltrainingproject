package com.example.finaltrainingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltrainingproject.entity.User;

public interface MyUserRepository extends JpaRepository<User, Long>{
		User findByUsername(String username);
}
