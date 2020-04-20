package com.biometry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties(allowGetters = true)
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deptId;
    @Column
    private String deptName;
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "dept")
    private List<StudentMaster> studs;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dept")
    private List<SubAdmin> subAdmins;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<StudentMaster> getStuds() {
        return studs;
    }

    public void setStuds(List<StudentMaster> studs) {
        this.studs = studs;
    }

    public Dept() {

    }

    public Dept(String deptName) {
		super();
		this.deptName = deptName;
	}

	@Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
