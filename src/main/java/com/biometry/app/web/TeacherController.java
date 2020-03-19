package com.biometry.app.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biometry.app.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@GetMapping({"","/handle-classroom"})
		public String getHandleClassroom(Model model) {
		Map<String, String> cmap = teacherService.readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
		model.addAttribute("classroomMap", cmap);	
		return "handle-classroom";
		}
	
	@RequestMapping(value="/class",method = RequestMethod.GET)
	public @ResponseBody void getIP(HttpSession session,@RequestParam("ip") String ip,@RequestParam("classroom") String classroom) {
		System.out.println("recieved module ip ="+ip);
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
	
}
