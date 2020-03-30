package com.biometry.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CourseMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cmID;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
   
    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherMaster teacher;
   

    public Integer getCmID() {
        return cmID;
    }

    public CourseMaster() {
    }

    @Override
    public String toString() {
        return "CourseMaster{" +
                "cmID=" + cmID +
                ", course=" + course +
                ", teacher=" + teacher +
                '}';
    }

    public void setCmID(Integer cmID) {
        this.cmID = cmID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeacherMaster getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherMaster teacher) {
        this.teacher = teacher;
    }

}
