/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.model;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author My Laptop
 */
public class Student {

    private String id;
    private String first_name;
    private String last_name;
    private Date dob;
    private String gender;
    private String major;
    private String class_id;
    private String email;
    private String address;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(String id, String first_name, String last_name, Date dob, String gender, String major, String class_id, String email, String address, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.major = major;
        this.class_id = class_id;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob + ", gender=" + gender + ", major=" + major + ", class_id=" + class_id + ", email=" + email + ", address=" + address + ", phone=" + phone + '}';
    }

    

    

   


}
