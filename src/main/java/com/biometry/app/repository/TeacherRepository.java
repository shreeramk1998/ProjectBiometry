package com.biometry.app.repository;

import com.biometry.app.entity.TeacherMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherMaster,Integer> {
}
