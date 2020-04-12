package com.biometry.app.web;

import com.biometry.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    
    @GetMapping(value= {"","department"})
    public String getManageDepartment(@RequestBody(required = false) Map<String,String> requestBody) {
        return "admin/manage-department";
    }
    
    @GetMapping("subadmin")
    public String getSubadmin() {
    	return "admin/manage-subadmin";
    }
    
//    @PostMapping(value = "/deleteAdmin")
//    @ResponseBody
//    public Map<String,String> deleteAdmins(@RequestBody Map<String,String> requestBody) {
//    	int response = adminService.deleteAdmins(requestBody);
//    	Map<String,String> responseBody=new HashMap<>();
//    	if(response==-1) {
//            responseBody.put("message","User does not exist.");
//            return responseBody;
//        }
//        else {
//        	responseBody.put("message","User deleted.");
//            return responseBody;
//        }
//    }
//
//    @PostMapping(value = "/addDept")
//    @ResponseBody
//    public Map<String,String> addDepts(@RequestBody Map<String,String> requestBody) {
//        int d_id = adminService.addDepts(requestBody);
//        Map<String,String> responseBody=new HashMap<>();
//        if(d_id==-1) {
//            responseBody.put("message","Department already exists!");
//            return responseBody;
//        }
//        else {
//            responseBody.put("d_id",Integer.toString(d_id));
//            return responseBody;
//        }
//    }
//    
//    @PostMapping(value = "/deleteDept")
//    @ResponseBody
//    public Map<String,String> deleteDepts(@RequestBody Map<String,String> requestBody) {
//    	int response = adminService.deleteDepts(requestBody);
//    	Map<String,String> responseBody=new HashMap<>();
//    	if(response==-1) {
//            responseBody.put("message","Department does not exist.");
//            return responseBody;
//        }
//        else {
//        	responseBody.put("message","Department deleted.");
//            return responseBody;
//        }
//    }
//
//    @PostMapping(value = "/addSubAdmin")
//    @ResponseBody
//    public Map<String,String> addSubAdmins(@RequestBody Map<String,String> requestBody) {
//        int sa_id = adminService.addSubAdmins(requestBody);
//        Map<String,String> responseBody=new HashMap<>();
//        if(sa_id==-1) {
//            responseBody.put("message","Sub-Admin already exists!");
//            return responseBody;
//        }
//        else {
//            responseBody.put("sa_id",Integer.toString(sa_id));
//            return responseBody;
//        }
//    }
//    
//    @PostMapping(value = "/deleteSubAdmin")
//    @ResponseBody
//    public Map<String,String> deleteSubAdmins(@RequestBody Map<String,String> requestBody) {
//    	int response = adminService.deleteSubAdmin(requestBody);
//    	Map<String,String> responseBody=new HashMap<>();
//    	if(response==-1) {
//            responseBody.put("message","User does not exist.");
//            return responseBody;
//        }
//        else {
//        	responseBody.put("message","User deleted.");
//            return responseBody;
//        }
//    }
}
