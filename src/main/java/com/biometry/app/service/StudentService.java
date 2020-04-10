package com.biometry.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.AttendanceMaster;
import com.biometry.app.entity.StudentMaster;
import com.biometry.app.repository.AttendanceMasterRepository;
import com.biometry.app.repository.StudentMasterRepository;

@Service
public class StudentService {

	@Autowired
	StudentMasterRepository studentMasterRepository;
	@Autowired
	AttendanceMasterRepository attendanceMasterRepository;
	public StudentMaster getByRollAndDiv(int id,String div) {
		return studentMasterRepository.findByRollAndDiv(id, div);
	}
	
	public boolean saveAttendances(Set<AttendanceMaster> set) {
		if(	attendanceMasterRepository.saveAll(set)!=null) {
			return true;
			
		}
		return false;
	}
	
}
