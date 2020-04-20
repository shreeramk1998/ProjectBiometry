package com.biometry.app.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.biometry.app.entity.TeacherMaster;
import com.biometry.app.repository.TeacherMasterRepository;
import com.biometry.app.service.TeacherService;
@Component("customSuccessRedirectionHandler")
public class CustomSuccessRedirectionHandler extends SimpleUrlAuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private  HttpSession session;
	@Autowired
	TeacherService teacherService;
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		if(response.isCommitted()) {
			System.out.println("\n Response already committed ... Cannot redirect \n");
		return;
		}
		this.session = request.getSession(true);
		
		redirectStrategy.sendRedirect(request, response, determineUrl(authentication));
	}

	private String determineUrl(Authentication auth) {
		List<String> roles = new ArrayList<String>();
		UserPrincipal up = (UserPrincipal) auth.getPrincipal();
		int userId = up.getUser().getId();
		
		auth.getAuthorities().forEach(s->roles.add(s.getAuthority()));
		if(isAdmin(roles)) {
			return "/admin";
		}else if(isSubAdmin(roles)) {
			return "/subAdmin";
		}else if(isTeacher(roles)) {
			TeacherMaster teacherMaster = teacherService.getByUserId(userId);
			if(teacherMaster!= null) {
				this.session.setAttribute("userSession", teacherMaster);
			}
			return "/teacher";
		}else if (isStudent(roles)) {
			return "/student";
		}else {
			return "/access-denied";
		}
	}

	private boolean isAdmin(List<String> roles) {
		if(roles.contains("ROLE_ADMIN"))
			return true;

		return false;
	}

	private boolean isSubAdmin(List<String> roles) {
		if(roles.contains("ROLE_SUBADMIN"))
		return true;
		return false;
	}

	private boolean isTeacher(List<String> roles) {
		if(roles.contains("ROLE_TEACHER"))
			return true;

		return false;
	}

	private boolean isStudent(List<String> roles) {
		if(roles.contains("ROLE_STUDENT"))
			return true;

		return false;
	}
}
