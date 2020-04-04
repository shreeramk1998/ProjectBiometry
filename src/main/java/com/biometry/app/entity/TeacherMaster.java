package com.biometry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table
@JsonIgnoreProperties(allowGetters = true)
public class TeacherMaster {
    public List<CourseMaster> getCourseMasters() {
        return courseMasters;
    }

    public void setCourseMasters(List<CourseMaster> courseMasters) {
        this.courseMasters = courseMasters;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherID;
    
    @Column(nullable=false)
    private String teacherName;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "teacher",orphanRemoval = true)
    private List<CourseMaster> courseMasters;

    @OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JoinColumn(name = "user_id")
    User user;
    
    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    
    public TeacherMaster() {
    }

    public TeacherMaster(Integer teacherID, String teacherName, List<CourseMaster> courseMasters, User user) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.courseMasters = courseMasters;
		this.user = user;
	}

	@Override
    public String toString() {
        return "TeacherMaster{" +
                "teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
