package com.anat.employee.db.entity;

import com.anat.employee.db.constant.Constant.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id @GeneratedValue @NotNull
    @Column(name = "emp_no")
    private Integer empNo;

    @NotNull @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Bangkok")
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull @Size(max = 14)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 16)
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    
    @NotNull @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Bangkok")
    @Column(name = "hire_date")
    private Date hireDate;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    
    @Override
    public String toString() {
        return this.empNo + " " + this.firstName + " " + this.lastName + " " + this.gender + " " + this.birthDate + " " + this.hireDate;
    }
    
}

