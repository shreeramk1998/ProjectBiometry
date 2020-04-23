package com.biometry.app.service;

import com.biometry.app.entity.AttendanceMaster;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.entity.Division;
import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.AttendanceMasterRepository;
import com.biometry.app.repository.CourseMasterRepository;
import com.biometry.app.repository.DivRepository;
import com.biometry.app.repository.TeacherMasterRepository;

import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherMasterRepository teacherMasterRepository;
    @Autowired
    DivRepository divRepository;
    @Autowired
    ReportService reportService;
    @Autowired
    CourseMasterRepository courseMasterRepository;
    @Autowired
    AttendanceMasterRepository attendanceMasterRepository;
    public static Map<String,String> classroomIP = new HashMap<String, String>();
	static {
		File folder = new File(System.getenv("BIOMETRY_HOME"));
		if(!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
		if(file.exists()) {
			System.out.println("hello map");
			classroomIP = readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
			
		}
	}
    
	public static Map<String, String> readFromMapFile(String mapFile) {
		Map<String, String> cm = new HashMap<String, String>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(mapFile);
			ois = new ObjectInputStream(fis);
			try {
				cm = (Map<String, String>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
			fis.close();


		} catch (FileNotFoundException e) {
			File file = new File(mapFile);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cm;
	}
	public boolean saveMapFile(Map<String, String> cm) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getenv("BIOMETRY_HOME")+"\\classroom.ser"));
			oos.writeObject(cm);
			oos.close();
			return true;
		} catch (FileNotFoundException e) {
			File file = new File(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public TeacherMaster getByUserId(int id ) {
		
		Optional<TeacherMaster> oteacherMaster = teacherMasterRepository.findByCurrentUser(id);
		if(oteacherMaster.isPresent()) {
		System.out.println(oteacherMaster.get());	
		return oteacherMaster.get();
		}
		else {
			return null;
		}
	}

	
    public List<Division> getAllDivisions(){
    	return divRepository.findAll();
    }
   
    public boolean preparePdfContent(int id,String name,int divId,int cmID) throws FileNotFoundException, JRException {
    	Map<String, Object> parameter = new HashMap<>();
    	parameter.put("teacherName", name);
    	parameter.put("className", divRepository.findById(id).get().getDivName());
    	CourseMaster cm = courseMasterRepository.findById(cmID).get();
    	parameter.put("courseName", cm.getCourse().getCourseName());
    	List <AttendanceMaster> list = attendanceMasterRepository.findAttendanceListByCourseMasterAndDivision(cmID,divId);
    	
    	
    	return reportService.exportReport(Integer.toString(id), parameter, list);
    }
    
    
}
