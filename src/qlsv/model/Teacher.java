/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.model;

import java.util.Date;

/**
 *
 * @author My Laptop
 */
public class Teacher {
    private String id;
    private String first_name;
    private String last_name;
    private String gender;
    private String phonenumber;
    private Date dob;
    private String department;
    private String degree;
    private String address;
    private String email;
    private String subject;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Teacher(String id, String first_name, String last_name, String gender, String phonenumber, Date dob, String department, String degree, String address, String email, String subject) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.dob = dob;
        this.department = department;
        this.degree = degree;
        this.address = address;
        this.email = email;
        this.subject = subject;
    }

    

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", phonenumber=" + phonenumber + ", dob=" + dob + ", department=" + department + ", degree=" + degree + ", address=" + address + ", email=" + email + ", subject=" + subject + '}';
    }

    
    
    
    
}
