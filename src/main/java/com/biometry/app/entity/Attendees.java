package com.biometry.app.entity;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Attendees {

	public Attendees() {
		super();
	}

	
	public Attendees(Set<AttendanceMaster> attendances) {
		super();
		this.attendances = attendances;
	}

	
	Set<AttendanceMaster> attendances;

	public Set<AttendanceMaster> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<AttendanceMaster> attendances) {
		this.attendances = attendances;
	}

}
