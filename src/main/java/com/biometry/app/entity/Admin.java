package com.biometry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(allowGetters = true)
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
    @OneToOne(cascade = CascadeType.ALL)
    User user;
    public Admin() {
    }

    public Admin(int adminId, User user) {
		super();
		this.adminId = adminId;
		this.user = user;
	}

    @Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", user=" + user + "]";
	}
    public int getAdminId() {
        return adminId;
    }


	public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
   
}
