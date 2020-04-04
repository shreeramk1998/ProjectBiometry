package com.biometry.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.StudentMaster;
import com.biometry.app.repository.StudentMasterRepository;

@Service
public class StudentMasterService {

	@Autowired
	StudentMasterRepository studentMasterRepository;
	public StudentMaster getByRollAndDIv(int id,String div) {
		return studentMasterRepository.findByRollAndDiv(id, div);
	}
	
	
}
