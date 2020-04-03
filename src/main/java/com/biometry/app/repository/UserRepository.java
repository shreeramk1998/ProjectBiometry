package com.biometry.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biometry.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
}
