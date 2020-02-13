package com.biometry.app.web;

import com.biometry.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "/login")
public class LoginController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login/{role}")
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> requestBody,@PathVariable int role) {
        Map<String,String> responseBody=new HashMap<>();
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
    }
}