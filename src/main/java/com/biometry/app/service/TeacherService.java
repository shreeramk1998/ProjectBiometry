package com.biometry.app.service;

import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.TeacherMasterRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherMasterRepository teacherMasterRepository;
    
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
