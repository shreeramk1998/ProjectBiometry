package com.biometry.app.repository;

import com.biometry.app.entity.Course;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.entity.TeacherMaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseMasterRepository extends JpaRepository<CourseMaster,Integer> {
	@Query("From CourseMaster cm where cm.teacher.teacherID = :id")
	Optional<List<CourseMaster>> findByTeacherId(@Param("id")int id);
}
