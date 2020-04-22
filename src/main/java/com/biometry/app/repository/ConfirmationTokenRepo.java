package com.biometry.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biometry.app.entity.ConfirmationToken;
import com.biometry.app.entity.User;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken , String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	boolean existsByUser(User user);
	ConfirmationToken findByUser(User user);
	@Transactional
	int deleteByUser(User user);
}