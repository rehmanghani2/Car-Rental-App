package com.app.carrentalapp.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.mysql.jdbc.Driver;
import com.app.carrentalapp.Models.CustomerModel;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;
import com.app.carrentalapp.Adapters.RecyclerCustomerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customers extends AppCompatActivity {
ArrayList<CustomerModel> arrCustomers = new ArrayList<>();
    private static  final String USER = "root";
    private static final String PASS = "@hm@dz@i123";
    private static final  String URL = "jdbc:mysql://192.168.239.157:3306/carrentalproject";
    private Connection sqlConnection;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recylerCustomers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MySQLDB database = new MySQLDB(Customers.this);
      //  database.initializeCustomers();
        arrCustomers = database.fetchCustomers();
        database.close();

        System.out.println(arrCustomers);
        RecyclerCustomerAdapter adapter = new RecyclerCustomerAdapter(Customers.this, arrCustomers);
        recyclerView.setAdapter(adapter);

    }

    public ArrayList<CustomerModel> getCustomersInfo(){
        int i = R.drawable.baseline_account_circle_70;
        ArrayList<CustomerModel> customers= new ArrayList<>();
        try(Connection sqlConnection = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Customers ")){
            while(rs.next()){
                CustomerModel customer = new CustomerModel(i,rs.getString("CustomerID"),rs.getString("Firstname"),
                        rs.getString("LastName"),rs.getString("Phone"),rs.getString("Email"),
                        rs.getString("Address"));
                customers.add(customer);
                System.out.println(rs);
            }
        } catch(SQLException se){
            System.out.println("Error in getting customers from sql "+se.getMessage());
        }
        return customers;
    }
}