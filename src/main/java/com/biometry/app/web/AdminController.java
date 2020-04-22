package com.biometry.app.web;

import com.biometry.app.entity.Admin;
import com.biometry.app.entity.Dept;
import com.biometry.app.entity.SubAdmin;
import com.biometry.app.entity.User;
import com.biometry.app.repository.AdminRepository;
import com.biometry.app.repository.ConfirmationTokenRepo;
import com.biometry.app.repository.DeptRepository;
import com.biometry.app.repository.SubAdminRepository;
import com.biometry.app.repository.TeacherMasterRepository;
import com.biometry.app.repository.UserRepository;
import com.biometry.app.service.AdminService;
import com.biometry.app.service.RegisterUser;
import com.biometry.app.service.UserService;

import net.bytebuddy.utility.RandomString;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    SubAdminRepository subAdminRepo;
    @Autowired
    DeptRepository deptRepo;
    @Autowired
    RegisterUser reg;
    @Autowired
    ConfirmationTokenRepo confRepo;
    
    @GetMapping(value= {""})
    public String getHomePage() {
        
    	return "admin/admin-home";
    }
    
    @GetMapping(value= {"department"})
    public String getManageDepartment(@RequestBody(required = false) Map<String,String> requestBody,Model model) {
        model.addAttribute("departmentList", adminService.getDeptRepository().findAll());
    	return "admin/manage-department";
    }
    
    @PostMapping("/department")
    @ResponseBody
    public boolean saveDepartment(@RequestBody Dept dept) {
    	System.out.println(dept);
    	if(adminService.addDepts(dept)) {
    		
    		return true;
    	}
    	return false;
    }
    
    @PostMapping("/update")
    @ResponseBody
    public boolean updateAdmin(@RequestBody Map<String,String> userDetails,HttpSession session) {
    	System.out.println(userDetails);
    	User updatedUser = adminService.updateAdmin(userDetails);
    	if(updatedUser!=null) {
    		session.setAttribute("currentUser",updatedUser);
    		return true;
    	}
    	return false;
    }
    @GetMapping("/manage-admin") 
    public String manageAdmin(Model model,HttpSession session) {
    	model.addAttribute("user", session.getAttribute("currentUser"));
    	return "admin/manage-admin";
    }
    
    @GetMapping("subadmin")
    public String getSubadmin(Model model) {
    	model.addAttribute("deptMap" , adminService.getAllDepartments());
    	return "admin/manage-subadmin";
    }
   
    
    @PostMapping("/new")
    @ResponseBody
    public String addAdmin(@RequestBody String username) {
    	System.out.println(username);
    	User user = userService.findByUsername(username);
//    	if(user!=null) return "already exists";
    	if(user==null) {
    		user = new User();
    		user.setRoles("ADMIN");
    		user.setUserName(username);
    		user.setPassword(RandomStringUtils.randomAlphabetic(5));
    		userRepo.save(user);
    		adminRepo.save(new Admin(user));
    	}
    	
    	String message = reg.confirmUserAccount(user);
    	return message;
    }
    @PostMapping("/subadmin")
    @ResponseBody
    public String addAdmin(@RequestBody Map<String,String> userMap) {
    	String username = userMap.get("username");
    	int departmentId = Integer.parseInt(userMap.get("department"));
    	Optional<Dept> odpt = deptRepo.findById(departmentId);
    	Dept dept = odpt.get();
    	if(dept==null) return "failed";
    	User user = userService.findByUsername(username);
//    	if(user!=null) return "already exists";
    	if(user==null) {
    		user = new User();
    		user.setRoles("SUBADMIN");
    		user.setUserName(username);
    		user.setPassword(RandomStringUtils.randomAlphabetic(5));
    		userRepo.save(user);
    		subAdminRepo.save(new SubAdmin(user,dept));
    	}
    	System.out.println(subAdminRepo.existsById(133));

    	String message = reg.confirmUserAccount(user);
    	return message;
    }
    
    
    @DeleteMapping("/sub-admin/{username}")
    @ResponseBody
    public boolean deleteSubadmin(@PathVariable("username") String username) {
    	System.out.println(username);
    	User u = userRepo.findByUserName(username);
    	if(u!=null) {    
    		System.out.println(confRepo.deleteByUser(u));
    		if(subAdminRepo.deleteByUser(u)>=1)
    		return true;
    	}
    	
    		return false;
    }
    @DeleteMapping("/admin/{username}")
    @ResponseBody
    public boolean deleteAdmin(@PathVariable("username") String username) {
    	System.out.println(username);
    	User u = userRepo.findByUserName(username);
    	if(u!=null) {    
    		System.out.println(confRepo.deleteByUser(u));
    		if(adminRepo.deleteByUser(u))
    		return true;
    	}
    	
    		return false;
    }
    
    
//    @PostMapping(value = "/deleteAdmin")
//    @ResponseBody
//    public Map<String,String> deleteAdmins(@RequestBody Dept dept) {
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
    @DeleteMapping(value = "/department/{id}")
    @ResponseBody
    public boolean deleteDept(@PathVariable int id) {
    	 if(adminService.deleteDepts(id)) {
    		 return true;
    	 }
    	 return false;
    }
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
