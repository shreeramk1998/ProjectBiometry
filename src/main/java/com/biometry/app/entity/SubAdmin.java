package com.biometry.app.entity;

import javax.persistence.*;

@Entity
@Table
public class SubAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subadId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Admin admin;
    @ManyToOne(cascade = CascadeType.ALL)
    private Dept dept;

    public SubAdmin() {
    }

    public Integer getSubadId() {
        return subadId;
    }

    public void setSubadId(Integer subadId) {
        this.subadId = subadId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "SubAdmin{" +
                "subadId=" + subadId +
                ", admin=" + admin +
                ", dept=" + dept +
                '}';
    }
}
