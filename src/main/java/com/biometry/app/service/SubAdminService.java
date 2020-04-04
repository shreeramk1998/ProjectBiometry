package com.biometry.app.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biometry.app.entity.Course;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.entity.Division;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.CourseMasterRepository;
import com.biometry.app.repository.CourseRepository;
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
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseMasterRepository courseMasterRepository;
	
//	public int checkLogin(Map<String,String> requestBody) {
//        String email = requestBody.get("email");
//        String password = requestBody.get("password");
//        Optional<SubAdmin> subadmin= subAdminRepository.findBySubadminEmailAndSubadminPassword(email, password);
//        if(subadmin.isPresent()) {
//            return subadmin.get().getSubadId();
//        }
//        else {
//            return -1;
//        }
//    }
//	
//	public int addDivs(Map<String,String> requestBody) {
//        String name = requestBody.get("name");
//        Optional<Division> div=divRepository.findByDivName(name);
//        if(!div.isPresent()) {
//            Division newDiv=new Division();
//            newDiv.setDivName(name);
//            divRepository.save(newDiv);
//            div = divRepository.findByDivName(name);
//            return div.get().getDivId();
//        }
//        else {
//            return -1;
//        }
//    }
//	
//	public int deleteDivs(Map<String,String> requestBody) {
//		String name = requestBody.get("name");
//		Optional<Division> div = divRepository.findByDivName(name);
//		if(div.isPresent()) {
//			divRepository.deleteById(div.get().getDivId());
//			return 1;
//		}
//		else
//			return -1;
//	}
//	
//	public int addTeachers(Map<String,String> requestBody) {
//		String name = requestBody.get("name");
//		String email = requestBody.get("email");
//		String password = requestBody.get("password");
//		Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherNameAndTeacherEmail(name, email);
//		if(!teacher.isPresent()) {
//			TeacherMaster newTeacher = new TeacherMaster();
//			newTeacher.setTeacherName(name);
//			newTeacher.setTeacherEmail(email);
//			newTeacher.setTeacherPass(password);
//			teacherMasterRepository.save(newTeacher);
//			teacher = teacherMasterRepository.findByTeacherName(name);
//			return teacher.get().getTeacherID();
//		}
//		else
//			return -1;
//	}
//	
//	public int deleteTeachers(Map<String,String> requestBody) {
//		String name = requestBody.get("name");
//		Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherName(name);
//		if(teacher.isPresent()) {
//			teacherMasterRepository.deleteById(teacher.get().getTeacherID());
//			return 1;
//		}
//		else
//			return -1;
//	}
//	
//	public int addCourses(Map<String,String> requestBody) {
//		String name = requestBody.get("name");
//		String year = requestBody.get("year");
//		Optional<Course> course = courseRepository.findByCourseName(name);
//		if(!course.isPresent()) {
//			Course newCourse = new Course();
//			newCourse.setCourseName(name);
//			newCourse.setYear(year);
//			courseRepository.save(newCourse);
//			course = courseRepository.findByCourseName(name);
//			return course.get().getCourseId();
//		}
//		else 
//			return -1;
//	}
//	
//	public int deleteCourses(Map<String,String> requestBody) {
//		String name = requestBody.get("name");
//		Optional<Course> course = courseRepository.findByCourseName(name);
//		if(course.isPresent()) {
//			courseRepository.deleteById(course.get().getCourseId());
//			return 1;
//		}
//		else 
//			return -1;
//	}
//	
//	public int assignCourses(Map<String,String> requestBody) {
//		String addcourse = requestBody.get("course");
//		String addteacher = requestBody.get("teacher");
//		Optional<Course> course = courseRepository.findByCourseName(addcourse);
//		Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherName(addteacher);
//		if(course.isPresent()&&teacher.isPresent()) {
//			Course c = new Course();
//			TeacherMaster t = new TeacherMaster();
//			c.setCourseId(course.get().getCourseId());
//			t.setTeacherID(teacher.get().getTeacherID());
//			CourseMaster newCourseMaster = new CourseMaster();
//			newCourseMaster.setCourse(c);
//			newCourseMaster.setTeacher(t);
//			courseMasterRepository.save(newCourseMaster);
//			Optional<CourseMaster> courseMaster = courseMasterRepository.findByCourseAndTeacher(c, t);
//			return courseMaster.get().getCmID();
//		}
//		else
//			return -1;
//	}
//	
//	public int removeCourseMap(Map<String,String> requestBody) {
//		String remcourse = requestBody.get("course");
//		String remteacher = requestBody.get("teacher");
//		Optional<Course> course = courseRepository.findByCourseName(remcourse);
//		Optional<TeacherMaster> teacher = teacherMasterRepository.findByTeacherName(remteacher);
//		if(course.isPresent()&&teacher.isPresent()) {
//			Course c = new Course();
//			TeacherMaster t = new TeacherMaster();
//			c.setCourseId(course.get().getCourseId());
//			t.setTeacherID(teacher.get().getTeacherID());
//			Optional<CourseMaster> courseMaster = courseMasterRepository.findByCourseAndTeacher(c, t);
//			courseMasterRepository.deleteById(courseMaster.get().getCmID());
//			return 1;
//		}
//		else
//			return -1;
//	}
}
