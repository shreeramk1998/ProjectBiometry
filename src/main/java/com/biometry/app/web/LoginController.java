package com.biometry.app.web;

import com.biometry.app.service.AdminService;
import com.biometry.app.service.SubAdminService;
import com.biometry.app.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/","/login"})

public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SubAdminService subAdminService;
    @Autowired
    private TeacherService teacherService;

    
    @GetMapping("")
    public String showLogin() {
    	return "login";
    }
    
    @RequestMapping(value="" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> requestBody) {
//    	Map<String,String> requestBody = body.get(0);
    	System.out.println( requestBody.keySet());
        Map<String,String> responseBody=new HashMap<>();
        int role = Integer.parseInt( requestBody.get("role"));
        /*
         * 0:admin
         * 1:subadmin
         * 2:teacher
         * 3:service
         * */
        if(role==0) {
            int id = adminService.checkLogin(requestBody);
            if (id == -1) {
                responseBody.put("message", "User does not exist!");
                return responseBody;
            } else {
                responseBody.put("id", Integer.toString(id));
                return responseBody;
            }
        }
        else if(role==1) {
        	int id = subAdminService.checkLogin(requestBody);
            if (id == -1) {
                responseBody.put("message", "User does not exist!");
                return responseBody;
            } else {
                responseBody.put("id", Integer.toString(id));
                return responseBody;
            }
        }
        else if(role == 2) {
        	int id = teacherService.checkLogin(requestBody);
            if (id == -1) {
                responseBody.put("message", "User does not exist!");
                return responseBody;
            } else {
                responseBody.put("id", Integer.toString(id));
                return responseBody;
            }
        }
        else if(role == 3) {
        	return null;
        }
        else
            return null;
    }
}