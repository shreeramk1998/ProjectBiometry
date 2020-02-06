package com.biometry.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biometry.app.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
