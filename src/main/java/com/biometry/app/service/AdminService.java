package com.biometry.app.service;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.Dept;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.repository.AdminRepository;
import com.biometry.app.repository.DeptRepository;
import com.biometry.app.repository.SubAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DeptRepository deptRepository;

    public DeptRepository getDeptRepository() {
		return deptRepository;
	}
	@Autowired
    private SubAdminRepository subAdminRepository;

	public Map<Integer,String> getAllDepartments() {
		List<Dept> depts = deptRepository.findAll();
		if(depts != null) {
			Map<Integer,String> deptMap = new HashMap<>();
			depts.forEach(d-> deptMap.put(d.getDeptId(), d.getDeptName()) );
			return deptMap;
		}
		return null;
	}
	
//    public int checkLogin(Map<String,String> requestBody) {
//        String email = requestBody.get("email");
//        String password = requestBody.get("password");
//        Optional<Admin> admin= adminRepository.findByAdminEmailAndAdminPassword(email,password);
//        if(admin.isPresent()) {
//            return admin.get().getAdminId();
//        }
//        else {
//            return -1;
//        }
//    }
//
//    public int addAdmins(Map<String,String> requestBody) {
//        String email = requestBody.get("email");
//        String password = requestBody.get("password");
//        Optional<Admin> admin=adminRepository.findByAdminEmail(email);
//        if(!admin.isPresent()) {
//            Admin newAdmin=new Admin();
//            newAdmin.setAdminEmail(email);
//            newAdmin.setAdminPassword(password);
//            adminRepository.save(newAdmin);
//            admin = adminRepository.findByAdminEmailAndAdminPassword(email,password);
//            return admin.get().getAdminId();
//        }
//        else {
//            return -1;
//        }
//    }
//    
//    public int deleteAdmins(Map<String,String> requestBody) {
//        String email = requestBody.get("email");
//        Optional<Admin> admin=adminRepository.findByAdminEmail(email);
//        if(admin.isPresent()) {
//        	adminRepository.deleteById(admin.get().getAdminId());
//        	return 1;
//        }
//        else {
//            return -1;
//        }
//    }
//
    public boolean addDepts(Dept dept) {
        String name = dept.getDeptName();
        Optional<Dept> odept=deptRepository.findByDeptName(name);
        if(!odept.isPresent()) {
            
            if(deptRepository.save(dept)!=null) {
            	return true;
            }
        }
        return false;
        
    }
//    
    public boolean deleteDepts(int id) {
        
        if(deptRepository.existsById(id)) {
        	deptRepository.delete(deptRepository.getOne(id));
        	return true;
        }
        else
        	return false;
    }
//
//    public int addSubAdmins(Map<String,String> requestBody) {
//        String email = requestBody.get("email");
//        String name = requestBody.get("name");
//        String password = requestBody.get("password");
//        Optional<Dept> dept = deptRepository.findByDeptName(name);
//        Optional<SubAdmin> subadmin = subAdminRepository.findBySubadminEmail(email);
//        if(!subadmin.isPresent()) {
//        	Dept d = new Dept();
//        	d.setDeptId(dept.get().getDeptId());
//        	SubAdmin newsub = new SubAdmin();
//        	newsub.setDept(d);
//        	newsub.setSubadminEmail(email);
//        	newsub.setSubadminPassword(password);
//        	subAdminRepository.save(newsub);
//        	subadmin = subAdminRepository.findBySubadminEmail(email);
//        	return subadmin.get().getSubadId();
//        }
//        else
//        	return -1;
//    }
//    
//    public int deleteSubAdmin(Map<String,String> requestBody) {
//    	String email = requestBody.get("email");
//    	Optional<SubAdmin> subadmin = subAdminRepository.findBySubadminEmail(email);
//    	if(subadmin.isPresent()) {
//    		subAdminRepository.deleteById(subadmin.get().getSubadId());
//    		return 1;
//    	}
//    	else
//    		return -1;
//    }
}
