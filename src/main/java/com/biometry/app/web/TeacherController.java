package com.biometry.app.web;

import java.util.Date;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
				System.out.println(jsonMap);
				SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
				Date date = simpleDate.parse(jsonMap.get("date"));
				SimpleDateFormat simpleTime = new SimpleDateFormat("hh:mm");
				Date time = simpleTime.parse(jsonMap.get("time"));
//		Date date = new Date(simpleDate.parse(jsonMap.get("date")).getTime());
//		//		
//		java.util.Date dtime = simpleTime.parse(jsonMap.get("time"));
//		Time time = new Time(dtime.getTime());
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
	@GetMapping("/manage-attendance")
	public String getManageAttendance(Model model,HttpSession session) {
		TeacherMaster tm =(TeacherMaster) session.getAttribute("userSession");
		
		model.addAttribute("courseMList", courseManagementService.getCourseMastersByTeacherId(tm.getTeacherID()));
		model.addAttribute("classList",teacherService.getAllDivisions()) ;
		return "teacher/manage-attendance";
	}
	
	@GetMapping("/get-report")
	public void getReport(HttpServletResponse response, HttpSession session,@RequestParam("courseM") int cmID,@RequestParam("class") int divId) throws IOException {
		System.out.println(cmID+" "+divId);
		TeacherMaster tm =(TeacherMaster) session.getAttribute("userSession");
		if(!teacherService.preparePdfContent(tm.getTeacherID(),tm.getTeacherName(), divId, cmID)) {
			response.sendRedirect("/teacher");
		}

		File file = new File(System.getenv("BIOMETRY_HOME") + "\\"+tm.getTeacherID()+".pdf");
		if (file.exists()) {

			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				//unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			//Here we have mentioned it to show as attachment
			//response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}
}
