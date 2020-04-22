package com.biometry.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biometry.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
	@Transactional
	int deleteByUserName(String userName);
	User findByUserNameIgnoreCase(String userName);
}
