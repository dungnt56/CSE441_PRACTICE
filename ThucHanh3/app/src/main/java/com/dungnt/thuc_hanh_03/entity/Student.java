package com.dungnt.thuc_hanh_03.entity;

import java.io.Serializable;

public class Student  implements Serializable {
    String id;
    Fullname full_name;
    String gender;
    String birth_date;
    String email;
    String address;
    String major;
    Float gpa;
    Integer year;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullnameStudent(){
        return full_name.getFirstName() + " " + full_name.getMiddleName() + " " + full_name.getLastName();
    }

    public Fullname getFullName() {
        return full_name;
    }

    public void setFullName(Fullname full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
