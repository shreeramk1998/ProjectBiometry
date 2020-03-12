package com.biometry.app.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biometry.app.service.SubAdminService;

@RestController("/subAdmin")
public class SubAdminController {
	@Autowired
	private SubAdminService subAdminService;
	
	@PostMapping(value = "/subAdmin/addDiv")
    @ResponseBody
    public Map<String,String> addDivs(@RequestBody Map<String,String> requestBody) {
        int d_id = subAdminService.addDivs(requestBody);
        Map<String,String> responseBody=new HashMap<>();
        if(d_id==-1) {
            responseBody.put("message","Division already exists!");
            return responseBody;
        }
        else {
            responseBody.put("d_id",Integer.toString(d_id));
            return responseBody;
        }
    }
	
	@PostMapping(value = "/subAdmin/deleteDiv")
	@ResponseBody
	public Map<String,String> deleteDivs(@RequestBody Map<String,String> requestBody) {
		int response = subAdminService.deleteDivs(requestBody);
    	Map<String,String> responseBody=new HashMap<>();
    	if(response==-1) {
            responseBody.put("message","Division does not exist.");
            return responseBody;
        }
        else {
        	responseBody.put("message","Division deleted.");
            return responseBody;
        }
	}
	
	@PostMapping(value = "/subAdmin/addTeacher")
    @ResponseBody
    public Map<String,String> addTeachers(@RequestBody Map<String,String> requestBody) {
        int t_id = subAdminService.addTeachers(requestBody);
        Map<String,String> responseBody=new HashMap<>();
        if(t_id==-1) {
            responseBody.put("message","Teacher already exists!");
            return responseBody;
        }
        else {
            responseBody.put("t_id",Integer.toString(t_id));
            return responseBody;
        }
    }
	
	@PostMapping(value = "/subAdmin/deleteTeacher")
	@ResponseBody
	public Map<String,String> deleteTeachers(@RequestBody Map<String,String> requestBody) {
		int response = subAdminService.deleteDivs(requestBody);
    	Map<String,String> responseBody=new HashMap<>();
    	if(response==-1) {
            responseBody.put("message","User does not exist.");
            return responseBody;
        }
        else {
        	responseBody.put("message","User deleted.");
            return responseBody;
        }
	}
	
	@PostMapping(value = "/subAdmin/addCourse")
	@ResponseBody
	public Map<String,String> addCourses(@RequestBody Map<String,String> requestBody) {
		int c_id = subAdminService.addCourses(requestBody);
		Map<String,String> responseBody=new HashMap<>();
		if(c_id==-1) {
			responseBody.put("message", "Course already added.");
			return responseBody;
		}
		else {
			responseBody.put("c_id", Integer.toString(c_id));
			return responseBody;
		}	
	}
	
	@PostMapping(value = "/subAdmin/deleteCourse")
	@ResponseBody
	public Map<String,String> deleteCourses(@RequestBody Map<String,String> requestBody) {
		int response = subAdminService.deleteCourses(requestBody);
    	Map<String,String> responseBody=new HashMap<>();
    	if(response==-1) {
            responseBody.put("message","Course does not exist.");
            return responseBody;
        }
        else {
        	responseBody.put("message","Course deleted.");
            return responseBody;
        }
	}
	
	@PostMapping(value = "/subAdmin/assignCourse")
	@ResponseBody
	public Map<String,String> assignCourses(@RequestBody Map<String,String> requestBody) {
		int cm_id = subAdminService.assignCourses(requestBody);
		Map<String,String> responseBody=new HashMap<>();
		if(cm_id==-1) {
			responseBody.put("message", "Course already assigned.");
			return responseBody;
		}
		else {
			responseBody.put("cm_id", Integer.toString(cm_id));
			return responseBody;
		}	
	}
	
	@PostMapping(value = "/subAdmin/removeCourseMap")
	@ResponseBody
	public Map<String,String> removeCourseMap(@RequestBody Map<String,String> requestBody) {
		int response = subAdminService.removeCourseMap(requestBody);
		Map<String,String> responseBody=new HashMap<>();
		if(response==-1) {
			responseBody.put("message", "Course mapping does not exist.");
			return responseBody;
		}
		else {
			responseBody.put("message", "Course mapping deleted.");
			return responseBody;
		}	
	}
}
