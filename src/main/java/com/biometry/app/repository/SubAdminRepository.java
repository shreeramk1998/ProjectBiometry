package com.biometry.app.repository;

import com.biometry.app.entity.SubAdmin;
import com.biometry.app.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

@Repository
public interface SubAdminRepository extends JpaRepository<SubAdmin,Integer> {
	@Transactional
	int deleteByUser(User u);
}
