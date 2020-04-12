package com.biometry.app.web;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biometry.app.BiometryExceptions.CourseManagementException;
import com.biometry.app.config.WebSocketHandler;
import com.biometry.app.entity.AttendanceMaster;
import com.biometry.app.entity.Attendees;
import com.biometry.app.entity.CourseMaster;
import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.entity.WebsocketMessage;
import com.biometry.app.service.CourseManagementService;
import com.biometry.app.service.StudentService;
import com.biometry.app.service.TeacherService;

@Controller
@RequestMapping("/teacher")
@SessionAttributes(names = "Attendees")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	CourseManagementService courseManagementService;
	@Autowired
	WebSocketHandler webSocketHandler;
	@Autowired
	StudentService studentService;

	@GetMapping({"","/handle-classroom"})
	public String getHandleClassroom(Model model,HttpSession session) {
		Map<String, String> cmap = teacherService.readFromMapFile(System.getenv("BIOMETRY_HOME")+"\\classroom.ser");
		//		model.addAttribute("teacherSession", session.getAttribute("userSession"));
		System.out.println("CMAP"+cmap);
		model.addAttribute("classroomMap", cmap);
		List<CourseMaster> cmList = courseManagementService.getCourseMastersByTeacherId(((TeacherMaster)session.getAttribute("userSession")).getTeacherID());
		model.addAttribute("courseMasterList", cmList);
		return "teacher/handle-classroom";
	}

	@PostMapping(consumes = "application/json" ,value = "/attendance")
	@ResponseBody
	public String saveAttendance(@RequestBody Map<String,String> jsonMap) throws ParseException {
		//		System.out.println(jsonMap);

		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(simpleDate.parse(jsonMap.get("date")).getTime());
		//		
		SimpleDateFormat simpleTime = new SimpleDateFormat("hh:mm");
		java.util.Date dtime = simpleTime.parse(jsonMap.get("time"));
		Time time = new Time(dtime.getTime());
		//		
		int cmID = Integer.parseInt(jsonMap.get("cmID"));
		String className = jsonMap.get("className");
		//		System.out.println("date "+ date+ " time "+time+" cmID "+cmID +" classNAme "+className);
		Map<String,Set<AttendanceMaster>> classAttendanceeMap = webSocketHandler.getClassAttendanceMap();
		Set<AttendanceMaster> set = classAttendanceeMap.get(className);
		try {
			set.forEach(am->
			{

				am.setCourseMaster(


						courseManagementService.getCourseMasterById(cmID)

						);
				am.setDate(date);
				am.setTime(time);
			}
					);
		}catch (CourseManagementException e) {
			return e.getMessage();
		}
		System.out.println("SET::"+set);
		if(studentService.saveAttendances(set)) {
			
			return "true";
		}
		return "false";

	}

	@SendTo("/topic/sample")
	@MessageMapping("/sensors")
	@CrossOrigin("*")
	public WebsocketMessage greeting(WebsocketMessage msg) throws Exception {

		System.out.println(msg);
		return msg;
	}
}
