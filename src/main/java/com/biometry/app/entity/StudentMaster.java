package com.biometry.app.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int roll;
    @Column(nullable=false)
    private Long phone;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="divId")
    private Division div;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="deptId")
    private Dept dept;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JoinColumn(name = "user_id")
    User user;
    
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

	@Override
	public String toString() {
		return "StudentMaster [studId=" + studId + ", studName=" + studName + ", roll=" + roll + ", phone=" + phone
				+ "]";
	}



}
