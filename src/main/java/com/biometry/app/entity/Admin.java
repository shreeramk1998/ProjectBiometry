package com.biometry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    User user;
    public Admin() {
    }

    public Admin(User user) {
		super();
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
