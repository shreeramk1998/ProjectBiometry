package com.biometry.app.entity;

import javax.persistence.*;

@Entity
@Table
public class SubAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subadId;
    
    @OneToOne(cascade = CascadeType.ALL)
    User user;
    
    @ManyToOne
    @JoinColumn(name="deptId")
    private Dept dept;
	public Integer getSubadId() {
		return subadId;
	}
	public void setSubadId(Integer subadId) {
		this.subadId = subadId;
	}
	
	public Dept getDept() {
		return dept;
	}
	public SubAdmin(Integer subadId, User user, Dept dept) {
		super();
		this.subadId = subadId;
		this.user = user;
		this.dept = dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "SubAdmin [subadId=" + subadId + 
				 ", dept=" + dept + "]";
	}
	

	public SubAdmin() {
		super();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
    
}
