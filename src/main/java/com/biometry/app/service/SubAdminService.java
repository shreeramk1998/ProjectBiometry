package com.biometry.app.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biometry.app.entity.Division;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.DivRepository;
import com.biometry.app.repository.SubAdminRepository;
import com.biometry.app.repository.TeacherMasterRepository;

@Service
public class SubAdminService {
	@Autowired
	private SubAdminRepository subAdminRepository;
	
	@Autowired
	private DivRepository divRepository;
	
	@Autowired
	private TeacherMasterRepository teacherMasterRepository;
	
	public int checkLogin(Map<String,String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Optional<SubAdmin> subadmin= subAdminRepository.findBySubadminEmailAndSubadminPassword(email, password);
        if(subadmin.isPresent()) {
            return subadmin.get().getSubadId();
        }
        else {
            return -1;
        }
    }
	
	public int addDivs(Map<String,String> requestBody) {
        String name = requestBody.get("name");
        Optional<Division> div=divRepository.findByDivName(name);
        if(!div.isPresent()) {
            Division newDiv=new Division();
            newDiv.setDivName(name);
            divRepository.save(newDiv);
            div = divRepository.findByDivName(name);
            return div.get().getDivId();
        }
        else {
            return -1;
        }
    }
	
	public int addTeachers(Map<String,String> requestBody) {
		String name = requestBody.get("name");
		String email = requestBody.get("email");
		String password = requestBody.get("password");
		Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherNameAndTeacherEmail(name, email);
		if(!teacher.isPresent()) {
			TeacherMaster newTeacher = new TeacherMaster();
			newTeacher.setTeacherName(name);
			newTeacher.setTeacherEmail(email);
			newTeacher.setTeacherPass(password);
			teacherMasterRepository.save(newTeacher);
			teacher = teacherMasterRepository.findByTeacherName(name);
			return teacher.get().getTeacherID();
		}
		else
			return -1;
	}
	
	/*
	public int addCourses(Map<String,String> requestBody) {
		
	}*/
}
