package com.biometry.app.web;

import com.biometry.app.service.AdminService;
import com.biometry.app.service.SubAdminService;
import com.biometry.app.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("/login")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SubAdminService subAdminService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> requestBody) {
        Map<String,String> responseBody=new HashMap<>();
        int role = Integer.parseInt(requestBody.get("role"));
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
        else
            return null;
    }
}