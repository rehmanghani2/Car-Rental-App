package com.app.carrentalapp.Models;

public class CustomerModel {
    public int img;
    public String customerID;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;
    public String address;
    public CustomerModel(int img, String customerID,String firstName,String lastName,String phone,String email,String address){
        this.img = img;
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

}
