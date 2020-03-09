package com.biometry.app.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.repository.SubAdminRepository;

@Service
public class SubAdminService {
	@Autowired
	private SubAdminRepository subAdminRepository;
	
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
}
