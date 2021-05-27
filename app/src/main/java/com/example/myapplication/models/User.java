package com.example.myapplication.models;

public class User {

    protected String id,fname,lastname,phone,email,pass,birthday;

    public User(String id, String fname, String lastname, String phone, String email, String pass, String birthday) {
        this.id = id;
        this.fname = fname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
        this.birthday = birthday;
    }

    public User(String id, String fname, String lastname, String phone, String email) {
        this.id = id;
        this.fname = fname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

