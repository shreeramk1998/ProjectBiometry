package com.biometry.app.repository;

import com.biometry.app.entity.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	Optional<Course> findByCourseName(String courseName);
	
}
