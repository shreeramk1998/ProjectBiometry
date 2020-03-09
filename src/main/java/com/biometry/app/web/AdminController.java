package com.biometry.app.web;

import com.biometry.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping(value="/addAdmin")
    @ResponseBody
    public Map<String,String> addAdmin(@RequestBody Map<String,String> requestBody) {
        int id = adminService.addAdmins(requestBody);
        Map<String,String> responseBody=new HashMap<>();
        if(id==-1) {
            responseBody.put("message","User already exists!");
            return responseBody;
        }
        else {
            responseBody.put("id",Integer.toString(id));
            return responseBody;
        }
    }

    @PostMapping(value = "/addDept")
    @ResponseBody
    public Map<String,String> addDepts(@RequestBody Map<String,String> requestBody) {
        int d_id = adminService.addDepts(requestBody);
        Map<String,String> responseBody=new HashMap<>();
        if(d_id==-1) {
            responseBody.put("message","Department already exists!");
            return responseBody;
        }
        else {
            responseBody.put("d_id",Integer.toString(d_id));
            return responseBody;
        }
    }

    @PostMapping(value = "/addSubAdmin")
    @ResponseBody
    public Map<String,String> addSubAdmins(@RequestBody Map<String,String> requestBody) {
        int sa_id = adminService.addSubAdmins(requestBody);
        Map<String,String> responseBody=new HashMap<>();
        if(sa_id==-1) {
            responseBody.put("message","Sub-Admin already exists!");
            return responseBody;
        }
        else {
            responseBody.put("sa_id",Integer.toString(sa_id));
            return responseBody;
        }
    }
}
