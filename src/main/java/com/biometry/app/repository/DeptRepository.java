package com.biometry.app.repository;

import com.biometry.app.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<Dept,Integer> {
    Optional<Dept> findByDeptName(String name);
    @Override
    default void deleteById(Integer id) {
    	// TODO Auto-generated method stub
    	
    }
}
