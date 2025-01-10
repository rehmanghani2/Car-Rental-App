package com.app.carrentalapp.Models;

public class CarModel {
    public String carID;
    public String make;
    public String model;
    String licencePlate;
    public String availabilityStatus;
    public int img;
    public int year;
    public double dailyRate;
    public CarModel(String carID, String make,String model,int year, String licencePlate,double dailyRate,
                    String availabilityStatus,int img){
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licencePlate = licencePlate;
        this.dailyRate = dailyRate;
        this.availabilityStatus = availabilityStatus;
        this.img = img;
    }
    public CarModel(String make, String model, int year, double dailyRate,int img){
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.img = img;
    }
}
