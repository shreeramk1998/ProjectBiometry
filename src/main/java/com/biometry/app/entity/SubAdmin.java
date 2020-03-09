package com.biometry.app.entity;

import javax.persistence.*;

@Entity
@Table
public class SubAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subadId;
    @Column(nullable=false)
    private String subadminEmail;
    @Column(nullable=false)
    private String subadminPassword;
    @ManyToOne
    @JoinColumn(name="deptId")
    private Dept dept;
	public Integer getSubadId() {
		return subadId;
	}
	public void setSubadId(Integer subadId) {
		this.subadId = subadId;
	}
	public String getSubadminEmail() {
		return subadminEmail;
	}
	public void setSubadminEmail(String subadminEmail) {
		this.subadminEmail = subadminEmail;
	}
	public String getSubadminPassword() {
		return subadminPassword;
	}
	public void setSubadminPassword(String subadminPassword) {
		this.subadminPassword = subadminPassword;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "SubAdmin [subadId=" + subadId + ", subadminEmail=" + subadminEmail + ", subadminPassword="
				+ subadminPassword + ", dept=" + dept + "]";
	}
	

	public SubAdmin() {
		super();
	}
	public SubAdmin(Integer subadId, String subadminEmail, String subadminPassword, Dept dept) {
		super();
		this.subadId = subadId;
		this.subadminEmail = subadminEmail;
		this.subadminPassword = subadminPassword;
		this.dept = dept;
	}
	
    
}
