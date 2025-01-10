package com.app.carrentalapp.Models;

import java.sql.Date;

public class TransactionModel {
    public String transactionID;
    public String carID;
    public String customerID;
    public String paymentStatus;
    public Date startDate;
    public Date endDate;
    public Double totalCost;
    public TransactionModel(String transactionID, String carID, String customerID, Date startDate,
                            Date endDate, Double totalCost, String paymentStatus){

        this.transactionID = transactionID;
        this.carID = carID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
    }

}
