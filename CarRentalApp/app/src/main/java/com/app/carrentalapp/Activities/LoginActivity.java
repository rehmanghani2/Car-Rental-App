package com.app.carrentalapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;

import java.sql.Date;

public class LoginActivity extends AppCompatActivity {
Button loginBtn;
TextView signUp;
EditText inputUsername, inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initializeStaff();
        initializeCars();
        initializeCustomers();
        initializeTransactions();


        loginBtn = findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.sigupText);

        inputUsername = findViewById(R.id.loginUn);
        inputPassword = findViewById(R.id.loginPass);


        Intent iMain = new Intent(LoginActivity.this, MainActivity.class);
        Intent signup = new Intent(LoginActivity.this, SignUp.class);




        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final MySQLDB database = new MySQLDB(LoginActivity.this);
                final String username = inputUsername.getText().toString();
                final String password = inputPassword.getText().toString();
                if(database.checkLoginValidity(username,password) && (!username.isEmpty() && !password.isEmpty())){
                    database.close();
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(iMain);
                }else if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please fill all fields ",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login UnSuccessful,", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this,"Username or Password is incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signup);
            }
        });
    }

    public  void initializeCustomers(){
        MySQLDB database = new MySQLDB(this);
        database.insertIntoCustomers("cu001","Ali","Khan","03010101010","ali@gmail.com","Street xyz,City ABC");
        database.insertIntoCustomers("cu002","Umar","Ayaz","03020202020","umar@gmail.com","Street dfg,City YUI");
        database.insertIntoCustomers("cu003","Usman","Ghani","03345678910","usman@gmail.com","Street dfg,City DFG");
        database.insertIntoCustomers("cu004","Faiz","Ahmad","03456443356","faiz@gmail.com","Street fgh,City ZXC");
        database.insertIntoCustomers("cu005","Huziafa","Khan","03045769922","huzaifa@gmail.com","Street lkj,City QWE");
        database.insertIntoCustomers("cu006","Hassan","Ali","03457676666","hassan@gmail.com","Street jkl,City DCV");
        database.insertIntoCustomers("cu007","Bilal","Khan","03066667777","bilal@gmail.com","Street bvn,City HJK");
        database.close();
    }
    public void initializeCars(){
        MySQLDB database = new MySQLDB(this);
        database.insertIntoCars("c001","Toyota","Camry",2020,"ABC123",50.00,"Available");
        database.insertIntoCars("c002","Ford","Mustang",2018,"XYZ123",60.00,"Available");
        database.insertIntoCars("c003","Honda","Civic",2021,"GHJ123",40.00,"Available");
        database.insertIntoCars("c004","Toyota","Corolla",2015,"KJH123",30.00,"Available");
        database.insertIntoCars("c005","Toyota","Land Cruiser",2010,"GFD123",70.00,"Booked");
        database.insertIntoCars("c006","Arden","Range Rover",2018,"CVB123",40.00,"Booked");
        database.insertIntoCars("c007","Brabus","Marcedez Benz",2020,"HJK123",50.00,"Booked");
        database.insertIntoCars("c008","Toyota","High Lander",2022,"REA123",60.00,"Booked");

//        insert into Cars(CarID,Make, Model, Year, LicencePlate, DailyRate,AvailabilityStatus)
        database.insertIntoCars("c009","Toyota","Corolla",2018,"ABC123",90.00,"Available");
        database.insertIntoCars("c010","Honda","Civic",2021,"XYZ123",80.00,"Available");
        database.insertIntoCars("c011","Honda","Civic",2021,"GHJ123",80.00,"Available");
        database.insertIntoCars("c012","Nissan","Altiam",2020,"KJH123",90.00,"Available");
        database.insertIntoCars("c013","Mazda","Mazda3",2010,"GFD123",90.00,"Available");


        database.close();
    }
    public void initializeTransactions(){
        MySQLDB database = new MySQLDB(this);
        //   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        database.insertIntoTransactions("t001","c001","cu001", new Date(2024-01-20),new Date(2024-01-25),
                50.00,"Paid");
        database.insertIntoTransactions("t002","c002","cu002", new Date(2024-02-20),new Date(2024-02-25),
                70.00,"Pending");
        database.insertIntoTransactions("t003","c003","cu003", new Date(2024-07-20),new Date(2024-07-25),
                80.00,"Pending");
        database.close();
    }
    public void initializeStaff(){
        MySQLDB database = new MySQLDB(this);
        database.insertIntoStaff("s001","RehmanGhani","Administrator","Rehman","12345");
        database.insertIntoStaff("s002","Usman","Staff","usman","54321");
        database.insertIntoStaff("s003","Dawood","Staff","dawood","xyz123");
        database.insertIntoStaff("s004","Ali","GuestUser","ali","abc123");
        database.insertIntoStaff("s005","Umar","GuestUser","umar","a1b2c3");
        database.insertIntoStaff("s006","Zakariya","GuestUser","zakariya","12345");
        database.insertIntoStaff("s007","FaizAhmad","GuestUser","faiz","54321");
        database.insertIntoStaff("s008","Huzaifa","GuestUser","huzaifa","67890");
        database.insertIntoStaff("s010","Ibrahim","GuestUser","ibrahim","76543");
        database.close();
    }
}