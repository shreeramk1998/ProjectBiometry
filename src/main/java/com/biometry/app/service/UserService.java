package com.biometry.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.User;
import com.biometry.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findByUsername(String username) {
		username = username.toLowerCase();
		return userRepository.findByUserName(username);
	}

}
