package com.biometry.app.entity;

import javax.persistence.*;

@Entity
@Table
public class CourseMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cmID;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private TeacherMaster teacher;
}
