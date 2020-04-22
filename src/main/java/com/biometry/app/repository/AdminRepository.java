package com.biometry.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.User;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	boolean deleteByUser(User user);
}
