package com.biometry.app.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class StudentMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studId;
    @Column(nullable=false)
    private String studName;
    @Column(nullable=false)
    private String studEmail;
    @Column(nullable=false)
    private Integer roll;
    @Column(nullable=false)
    private Long phone;
    @Column(nullable=false)
    private String studPass;
    @ManyToOne
    @JoinColumn(name="divId")
    private Division div;
    @ManyToOne
    @JoinColumn(name="deptId")
    private Dept dept;

    public List<AttendanceMaster> getAttendanceMasters() {
        return attendanceMasters;
    }

    public void setAttendanceMasters(List<AttendanceMaster> attendanceMasters) {
        this.attendanceMasters = attendanceMasters;
    }

    @ManyToMany(mappedBy = "studentMasterList")
    private List<AttendanceMaster> attendanceMasters;

    public Division getDiv() {
        return div;
    }

    public void setDiv(Division div) {
        this.div = div;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public StudentMaster() {
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getStudPass() {
        return studPass;
    }

    public void setStudPass(String studPass) {
        this.studPass = studPass;
    }


}
