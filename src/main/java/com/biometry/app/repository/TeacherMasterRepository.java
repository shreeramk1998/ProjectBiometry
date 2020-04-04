package com.biometry.app.repository;

import com.biometry.app.entity.TeacherMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherMasterRepository extends JpaRepository<TeacherMaster,Integer> {
	@Query("select tm FROM TeacherMaster tm where tm.user.id = :uid")
	public Optional<TeacherMaster> findByCurrentUser(@Param("uid") int id);
}
