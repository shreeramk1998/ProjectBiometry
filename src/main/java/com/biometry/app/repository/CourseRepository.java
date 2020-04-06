package com.biometry.app.repository;

import com.biometry.app.entity.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	Optional<Course> findByCourseName(String courseName);
	List<Course> findByCourseIdIn(List<Integer>ids);
	
}
