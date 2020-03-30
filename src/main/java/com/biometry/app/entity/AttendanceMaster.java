package com.biometry.app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table
public class AttendanceMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="smid")
    private StudentMaster studentMaster;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cmid")
    private CourseMaster courseMaster;
    @Column
    private Date date;
    

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
				+ courseMaster + ", date=" + date + "]";
	}

	public AttendanceMaster(Integer attId, StudentMaster studentMaster, CourseMaster courseMaster, Date date) {
		super();
		this.attId = attId;
		this.studentMaster = studentMaster;
		this.courseMaster = courseMaster;
		this.date = date;
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


   
}
