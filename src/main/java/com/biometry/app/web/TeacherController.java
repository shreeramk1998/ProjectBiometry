package com.biometry.app.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biometry.app.entity.WebsocketMessage;
import com.biometry.app.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;

	@GetMapping({"","/handle-classroom"})
	public String getHandleClassroom(Model model,HttpSession session) {
		Map<String, String> cmap = teacherService.readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
//		model.addAttribute("teacherSession", session.getAttribute("userSession"));
		System.out.println("CMAP"+cmap);
		model.addAttribute("classroomMap", cmap);
		return "handle-classroom";
	}

	

	@SendTo("/topic/sample")
	@MessageMapping("/sensors")
	@CrossOrigin("*")
	public WebsocketMessage greeting(WebsocketMessage msg) throws Exception {

		System.out.println(msg);
		return msg;
	}
}
