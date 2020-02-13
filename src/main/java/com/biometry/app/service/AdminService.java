package com.biometry.app.service;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.Dept;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.repository.AdminRepository;
import com.biometry.app.repository.DeptRepository;
import com.biometry.app.repository.SubAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private SubAdminRepository subAdminRepository;

    public int checkLogin(Map<String,String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Optional<Admin> admin= adminRepository.findByAdminEmailAndAdminPassword(email,password);
        if(admin.isPresent()) {
            return admin.get().getAdminId();
        }
        else {
            return -1;
        }
    }

    public int addAdmins(Map<String,String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Optional<Admin> admin=adminRepository.findByAdminEmail(email);
        if(!admin.isPresent()) {
            Admin newAdmin=new Admin();
            newAdmin.setAdminEmail(email);
            newAdmin.setAdminPassword(password);
            adminRepository.save(newAdmin);
            admin = adminRepository.findByAdminEmailAndAdminPassword(email,password);
            return admin.get().getAdminId();
        }
        else {
            return -1;
        }
    }

    public int addDepts(Map<String,String> requestBody) {
        String name = requestBody.get("name");
        Optional<Dept> dept=deptRepository.findByDeptName(name);
        if(!dept.isPresent()) {
            Dept newDept=new Dept();
            newDept.setDeptName(name);
            deptRepository.save(newDept);
            dept = deptRepository.findByDeptName(name);
            return dept.get().getDeptId();
        }
        else {
            return -1;
        }
    }

    public int addSubAdmins(Map<String,String> requestBody) {
        String email = requestBody.get("email");
        String name = requestBody.get("dept");
        Optional<Admin> admin = adminRepository.findByAdminEmail(email);
        Optional<Dept> dept = deptRepository.findByDeptName(name);
        if(!admin.isPresent()&&!dept.isPresent()) {
            SubAdmin subAdmin = new SubAdmin();
            subAdmin.setAdmin(admin.get());
            subAdmin.setDept(dept.get());
            subAdminRepository.save(subAdmin);
            Optional<SubAdmin> subAdmins = subAdminRepository.findSubAdminByAdmin_AdminEmailAndDept_DeptName(admin,dept);
            System.out.println(subAdmins.get().getSubadId());
            return subAdmins.get().getSubadId();
        }
        else
            return -1;
    }
}
