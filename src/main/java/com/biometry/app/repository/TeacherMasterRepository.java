package com.biometry.app.repository;

import com.biometry.app.entity.TeacherMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherMasterRepository extends JpaRepository<TeacherMaster,Integer> {
	Optional<TeacherMaster> findByTeacherNameAndTeacherEmail(String teacherName,String teacherEmail);
	Optional<TeacherMaster> findByTeacherName(String teacherName);
	Optional<TeacherMaster> findByTeacherEmailAndTeacherPass(String teacherEmail,String teacherPassword);
}
