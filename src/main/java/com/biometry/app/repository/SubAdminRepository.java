package com.biometry.app.repository;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.Dept;
import com.biometry.app.entity.SubAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubAdminRepository extends JpaRepository<SubAdmin,Integer> {
    Optional<SubAdmin> findSubAdminByAdmin_AdminEmailAndDept_DeptName(Optional<Admin> admin, Optional<Dept> dept);
}
