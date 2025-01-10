package com.app.carrentalapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.mysql.cj.jdbc.Driver;
import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.R;
import com.app.carrentalapp.Adapters.RecyclerAvailCarAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AvailableCars extends AppCompatActivity {
    int[] images= {R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
                   R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
                  R.drawable.toyota_land_cruiser_200_grey, R.drawable.arden_range_rover2006,
                  R.drawable.bmw,R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car};

private static  final String USER = "root";
private static final String PASS = "@hm@dz@i";
private static final  String URL = "jdbc:mysql://192.168.239.157:3306/carrentalproject";
Toolbar toolbar;
 //private Connection sqlConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_available_cars);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.homeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


            MySQLDB database = new MySQLDB(AvailableCars.this);
          //  database.initializeCars();
            ArrayList<CarModel> carList = database.fetchAvaialbleCars();
             for(int i=0; i<carList.size();i++){
                 Log.d("Cars Info: ", "IMG: "+carList.get(i).img+ "ID: "+ carList.get(i).carID
                      +"Make : "+carList.get(i).make+ "Model: "+ carList.get(i).model);
             }
            database.close();


        Log.d("Cars ",carList.toString());

        RecyclerAvailCarAdapter adapter = new RecyclerAvailCarAdapter(AvailableCars.this,carList);
        recyclerView.setAdapter(adapter);

        // Optional: Set up the back button to go back to MainActivity
//        if(getSupportActionBar()!=null) {
//            getSupportActionBar().setTitle("Available Cars");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Custom back press logic here
                // For example, navigate back to MainDrawerActivity
                Intent intent = new Intent(AvailableCars.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the OptionActivity
            }
        });



//      adapter.notifyDataSetChanged();

      //  carList.add(new CarModel())
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Optionally, you can finish the activity explicitly here if you want to ensure it's closed
        finish();
    }

    public ArrayList<CarModel> getAvailCars(){
        int i =0;
        ArrayList<CarModel> availCars = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection sqlConnection = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = sqlConnection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Cars where AvailabilityStatus = 'Available' ");
            while(rs.next()){
                CarModel car = new CarModel(rs.getString("CarID"),rs.getString("Make"),
                        rs.getString("Model"),rs.getInt("Year"),
                        rs.getString("LicencePlate"),rs.getDouble("DailyRate")*250,
                        rs.getString("AvailabilityStatus"),images[i++]);
                availCars.add(car);
                System.out.println(rs);
            }
        } catch(SQLException se){
            System.out.println("Error in getting cars from sql "+se.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return availCars;
    }


}