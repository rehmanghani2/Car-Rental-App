package com.app.carrentalapp.Models;

public class StaffModel {
    public String staffId, name, role, username, password;
    public StaffModel(String staffId,String name,String role,String username,String password){
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
