package com.biometry.app.service;

import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.TeacherMasterRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherMasterRepository teacherMasterRepository;
    
    public int checkLogin(Map<String,String> requestBody) {
    	String email = requestBody.get("email");
    	String password = requestBody.get("password");
    	Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherEmailAndTeacherPass(email, password);
    	if(teacher.isPresent()) {
    		return teacher.get().getTeacherID();
    	}
    	else
    		return -1;
    }
}
