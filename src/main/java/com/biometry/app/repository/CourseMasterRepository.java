package com.biometry.app.repository;

import com.biometry.app.entity.Course;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.entity.TeacherMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMasterRepository extends JpaRepository<CourseMaster,Integer> {
	Optional<CourseMaster> findByCourseAndTeacher(Course course,TeacherMaster teacher);
}
