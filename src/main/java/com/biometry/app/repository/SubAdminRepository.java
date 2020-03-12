package com.biometry.app.repository;

import com.biometry.app.entity.SubAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubAdminRepository extends JpaRepository<SubAdmin,Integer> {
	Optional<SubAdmin> findBySubadminEmail(String subadminEmail);
	Optional<SubAdmin> findBySubadminEmailAndSubadminPassword(String subadminEmail,String subadminPassword);
	Optional<SubAdmin> deleteBySubadminEmail(String subadminEmail);
}
