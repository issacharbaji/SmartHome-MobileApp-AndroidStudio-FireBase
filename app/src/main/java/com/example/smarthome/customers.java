package com.example.smarthome;

public class customers {

    public String name, lastName, phone,email,idNumber,pin,dob,role;


    public customers() {

    }

    public customers(String name, String lastName, String phone, String email, String idNumber, String pin, String dob,String role) {

        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

        this.idNumber = idNumber;
        this.pin = pin;
        this.dob = dob;
        this.role= role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getLocation() {
        return role;
    }

    public void setLocation(String location) {
        this.role = location;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
