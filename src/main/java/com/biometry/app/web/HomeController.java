package com.biometry.app.web;

import com.biometry.app.config.WebSocketHandler;
import com.biometry.app.entity.Admin;
import com.biometry.app.entity.AttendanceMaster;
import com.biometry.app.entity.ConfirmationToken;
import com.biometry.app.entity.User;
import com.biometry.app.repository.ConfirmationTokenRepo;
import com.biometry.app.repository.UserRepository;
import com.biometry.app.service.AdminService;
import com.biometry.app.service.CourseManagementService;
import com.biometry.app.service.SubAdminService;
import com.biometry.app.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/","/login"})

public class HomeController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SubAdminService subAdminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
	CourseManagementService courseManagementService;
	@Autowired
	WebSocketHandler webSocketHandler;
	@Autowired
	ConfirmationTokenRepo confirmationTokenRepo;
    @Autowired
    UserRepository userRepository;
    @GetMapping("")
    public String showLogin() {
    	return "login";
    }
    
    @RequestMapping(value="/class",method = RequestMethod.GET)
	public @ResponseBody void getIP(HttpSession session,@RequestParam("ip") String ip,@RequestParam("classroom") String classroom) {
		System.out.println("\n 	recieved module ip ="+ip);
		TeacherService.classroomIP.put(classroom, ip);

		teacherService.saveMapFile(TeacherService.classroomIP);
		System.out.println(teacherService.readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser"));
		//		return "redirect:/teacher";
	}
	@GetMapping("/getClassroomIp")
	public @ResponseBody Map<String, String>getClassroomIp(Model model) {
		Map<String, String> cmap = teacherService.readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
		model.addAttribute("classroomMap", cmap);
		return cmap;
	}
	@GetMapping("/lol")
	@ResponseBody 
	public String hello() {
		return "hello";
	} 
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount(Model model , @RequestParam("token") String token) {
		ConfirmationToken confToken = confirmationTokenRepo.findByConfirmationToken(token);
		if(confToken!=null) {
			User user = userRepository.findByUserNameIgnoreCase(confToken.getUser().getUserName());
            user.setEnabled(true);
            userRepository.save(user);
            confirmationTokenRepo.delete(confToken);
			model.addAttribute("activationMessage", "Successfully activated please");
		}
		return "login";
	}
	
    
//    @RequestMapping(value="" , method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,String> login(@RequestBody Map<String,String> requestBody,Model model) {
//    	System.out.println( requestBody );
//        Map<String,String> responseBody=new HashMap<>();
//        int role = Integer.parseInt( requestBody.get("role"));
//        int id;
//        /*
//         * 0:admin
//         * 1:subadmin
//         * 2:teacher
//         * 3:service
//         * */
//        if(role==0) {
//            id = adminService.checkLogin(requestBody);
//            if (id == -1) {
//            	
//          responseBody.put("message", "invalid credentials!");
//          
//            
//            } else {
//            	responseBody.put("url","/admin");
//            }
//        }
//        else if(role==1) {
//        	id = subAdminService.checkLogin(requestBody);
//            if (id == -1) {
//                responseBody.put("message", "invalid credentials!!");
//                
//            } else {
//            	responseBody.put("url","/subAdmin");
//            }
//        }
//        else if(role == 2) {
//        	 id = teacherService.checkLogin(requestBody);
//            if (id == -1) {
//                responseBody.put("message", "invalid credentials!");
//
//            } else {
//            	responseBody.put("url","/teacher");
//
//            }
//        }
//        else if(role == 3) {
//        	//student check to be added 
//        }
//        else {
//        	responseBody.put("message", "invalid submission please retry!");
//        }
        
//        return responseBody;
//    	return requestBody;
//    }
}