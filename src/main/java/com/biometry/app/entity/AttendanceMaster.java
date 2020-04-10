package com.biometry.app.entity;

import javax.persistence.*;

import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Time;


@Entity
@Table
public class AttendanceMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attId;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="smid")
    private StudentMaster studentMaster;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cmid")
    private CourseMaster courseMaster;
    
    @Column
    private Date date;
    
    @Column
    private Time time;
    

    public AttendanceMaster() {
    }

    public Integer getAttId() {
        return attId;
    }

    public void setAttId(Integer attId) {
        this.attId = attId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	@Override
	public String toString() {
		return "AttendanceMaster [attId=" + attId + ", studentMaster=" + studentMaster + ", courseMaster="
				+ courseMaster + ", date=" + date + ", time=" + time + "]\n";
	}

	

	public AttendanceMaster(Integer attId, StudentMaster studentMaster, CourseMaster courseMaster, Date date,
			Time time) {
		super();
		this.attId = attId;
		this.studentMaster = studentMaster;
		this.courseMaster = courseMaster;
		this.date = date;
		this.time = time;
	}

	public StudentMaster getStudentMaster() {
		return studentMaster;
	}

	public void setStudentMaster(StudentMaster studentMaster) {
		this.studentMaster = studentMaster;
	}

	public CourseMaster getCourseMaster() {
		return courseMaster;
	}

	public void setCourseMaster(CourseMaster courseMaster) {
		this.courseMaster = courseMaster;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((studentMaster == null) ? 0 : studentMaster.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttendanceMaster other = (AttendanceMaster) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (studentMaster == null) {
			if (other.studentMaster != null)
				return false;
		} else if (!studentMaster.equals(other.studentMaster))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}


}
