package com.example.smarthome;

public class users {


    public String name, email, role,reference;

    public users(){

    }

    public users(String name, String email, String role,String reference) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.reference=reference;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
