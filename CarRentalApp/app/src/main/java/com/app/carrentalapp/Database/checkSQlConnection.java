package com.app.carrentalapp.Database;

import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

 public class checkSQlConnection {
    static int[] images= {R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
            R.drawable.toyota_land_cruiser_200_grey, R.drawable.arden_range_rover2006,
            R.drawable.bmw,R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car};
    static ArrayList<CarModel> carList = new ArrayList<>();
    private static  final String USER = "root";
    private static final String PASS = "@hm@dz@i123";
    private static final  String URL = "jdbc:mysql://127.0.0.1:3306/carrentalproject";
    private static Connection sqlConnection;


    public static ArrayList<CarModel> getAvailCars(){
        System.out.println("get Cars");
        int i =0;
        ArrayList<CarModel> availCars = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection sqlConnection = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Cars where AvailabilityStatus ='Available' ");
            while(rs.next()){
                CarModel car = new CarModel(rs.getString("CarID"),rs.getString("Make"),
                        rs.getString("Model"),rs.getInt("Year"),
                        rs.getString("LicencePlate"),rs.getDouble("DailyRate")*250,
                        rs.getString("AvailabilityStatus"),images[i++]);
                availCars.add(car);
                System.out.println(rs);
            }
            rs.close();
            stmt.close();
            sqlConnection.close();
        } catch(SQLException se){
            System.out.println("Error in getting cars from sql "+se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return availCars;
    }
    public static void main(String[] args) {

        carList = getAvailCars();
        System.out.println(carList);
    }




}
