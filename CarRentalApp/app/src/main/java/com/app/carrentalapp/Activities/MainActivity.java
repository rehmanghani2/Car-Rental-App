package com.app.carrentalapp.Activities;

import android.content.Intent;
import android.os.Bundle;
// import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
// import androidx.annotation.NonNull;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.carrentalapp.Fragments.AboutUsFragment;
import com.app.carrentalapp.Fragments.AvailableCarsFragment;
import com.app.carrentalapp.Fragments.BookedCarFragment;
import com.app.carrentalapp.Fragments.CustomersFragment;
import com.app.carrentalapp.Fragments.HomeFragment;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Fragments.HelpAndSupportFragment;
import com.app.carrentalapp.R;
import com.app.carrentalapp.Fragments.SearchFragment;
import com.app.carrentalapp.Fragments.TransactionsFragment;
import com.google.android.material.navigation.NavigationView;

import java.sql.Date;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
  //  private ExecutorService executorService;

    //    @override
//    onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Access the view after setContentView()
//        val myView = findViewById<View>(R.id.my_view)
//                myView?.setOnApplyWindowInsetsListener(...)
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        toolbar = findViewById(R.id.toolbar);


        // Step 1
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);
      //  new DatabaseTask().execute();
        toggle.syncState();
        // Default Fragment
        loadFragment(new HomeFragment(), 0);

        Intent iAvailCars = new Intent(MainActivity.this, AvailableCars.class);
        Intent iCustomers = new Intent(MainActivity.this, Customers.class);
        Intent iLogout =new Intent(MainActivity.this, LoginActivity.class);
        Intent iTransaction = new Intent(MainActivity.this, Transactions.class);
        // Get the onBackPressedDispatcher
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        // add a callback to handle the back button pressed
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // handle the back button pressed here
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
//                else {
//                    super.handleOnBackPressed();
//                }
            }
        });

        initializeCars();
        initializeCustomers();
        initializeTransactions();
        initializeStaff();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.optHome) {

                toolbar.setTitle("Home");
                loadFragment(new HomeFragment(), 1);
                if (getSupportActionBar() != null) {
                    // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Home");
                    //    OnBackPressedDispatcher onBackPressedDispatcher = getSupportFragmentManager().
                }
                //           OnBackPressedDispatcher onBackPressedDispatcher = getSupportFragmentManager().OnBackPressedDispatcher();

            } else if (id == R.id.optAvaialbleCars) {

                toolbar.setTitle("AvailableCars");
               loadFragment(new AvailableCarsFragment(),1);
              //  startActivity(iAvailCars);
                if (getSupportActionBar() != null) {
                    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("AvailableCars");
                }

            } else if (id == R.id.optBookmarked) {

                toolbar.setTitle("Bookmarked");
                loadFragment(new BookedCarFragment(),1);
                if (getSupportActionBar() != null) {
                    // this add back button
                    //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Bookmarked");
                }


            } else if (id == R.id.optSearch) {

                toolbar.setTitle("Search");
                loadFragment(new SearchFragment(), 1);
                if (getSupportActionBar() != null) {
                    //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Search");

                }

            }  else if (id == R.id.optCustomers) {

                toolbar.setTitle("Customers");
            //    startActivity(iCustomers);
                loadFragment(new CustomersFragment(),1);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Customers");
                }
            } else if (id == R.id.optPayment) {
                toolbar.setTitle("Transactions");
             //   startActivity(iTransaction);
                loadFragment(new TransactionsFragment(), 1);
                if (getSupportActionBar() != null) {
                    //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Transactions");
                }
            } else if (id == R.id.optHelpAndSupport) {

              //  toolbar.setTitle("Help And Feedba");
                loadFragment(new HelpAndSupportFragment(), 1);
                if (getSupportActionBar() != null) {
                    //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Help And Support");
                }

            } else if (id == R.id.optAboutUs) {

                toolbar.setTitle("About Us");
                loadFragment(new AboutUsFragment(), 1);
                if (getSupportActionBar() != null) {
                    //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("About Us");
                }

            }
            else {
                // logout logic
                toolbar.setTitle("Log out ");
                // Get the FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Pop all fragments from the back stack
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // Optionally, replace the current fragment with a new one (e.g., a login screen or home screen)
                FragmentTransaction transaction = fragmentManager.beginTransaction();
               // transaction.replace(R.id.fragment_container, new LoginFragment());  // Replace with login screen
                transaction.commit();

                // Handle your logout logic, like clearing shared preferences, etc.
                // Example: SharedPreferences.clear();
                startActivity(iLogout);
                if (getSupportActionBar() != null) {
                    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setTitle("Logout");

                }
            }
            // close the navigation drawer
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }
    public void loadFragment(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        // Pop all fragments from the back stack

        if( flag == 0 ) {

            ft.add(R.id.container, fragment);
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
           ft.addToBackStack("root_fragment");

        }
        else {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.replace(R.id.container, fragment);
            ft.addToBackStack(null);
        }
        ft.commit();
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
//    @Override
//    protected void onDestroy() {
//      //  super.onDestroy();
//        // Properly shut down the ExecutorService when the activity is destroyed
//     //   if (executorService != null) {
//    //        executorService.shutdown();
//        }
    }


// Initialize ExecutorService with a fixed thread pool of 2 threads
//  executorService = Executors.newFixedThreadPool(2);

// Start the database connection task in the background
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Connection connection = getConnection();  // Call your database connection method
//                    if (connection != null) {
//                        // Handle success (run on the main thread)
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                // Update UI with success message
//                                startActivity(iAvailCars);
//                            }
//                        });
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    // Handle failure (run on the main thread)
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Update UI with failure message
//                        }
//                    });
//                }
//            }
//        });




//    public static Connection getConnection() throws SQLException {
//        try {
//            // Load MySQL JDBC driver (optional in newer versions)
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Return connection
//            return DriverManager.getConnection("jdbc:mysql://192.168.239.157:3306/carrentalproject", "root", "@hm@dz@i123");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found", e);
//        }
//    }

//        try {
//         //   Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carrentalproject", "root", "@hm@dz@i123");
//        } catch (SQLException e) {
//            e.getMessage();  // This will print detailed SQL exceptions
//            throw e;
//        }
//    }
//    public void onBackPressed(){
//
//        super.onBackPressed();
//    }
