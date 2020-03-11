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
}
