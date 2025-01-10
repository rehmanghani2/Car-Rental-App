package com.app.carrentalapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.Models.CustomerModel;
import com.app.carrentalapp.Models.TransactionModel;
import com.app.carrentalapp.R;
import com.app.carrentalapp.Models.StaffModel;

import java.sql.Date;
import java.util.ArrayList;

public class MySQLDB extends SQLiteOpenHelper {
    int[] images= {R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
            R.drawable.toyota_land_cruiser_200_grey, R.drawable.arden_range_rover2006,
            R.drawable.bmw,R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
            R.drawable.toyota_land_cruiser_200_grey};
    private static final String DB_NAME = "CarRentalDB";
    private static  final int DB_VERSION = 2;
    //  Cars TABLE
    private static final String TABLE_CARS = "Cars";
    private  static  final String PKEY_CarID = "CarID";
    private  static  final String KEY_Make = "Make";
    private  static  final String KEY_Model = "Model";
    private  static  final String KEY_Year = "Year";
    private  static  final String KEY_LicencePlate = "LicencePlate";
    private  static  final String KEY_DailyRate = "DailyRate";
    private  static  final String KEY_AvailabilityStatus = "AvailabilityStatus";



    // Customers Table
    private static final String TABLE_CUSTOMERS = "Customers";
    private static final String PKEY_CustomerID = "CustomerID";
    private static final String KEY_FirstName = "FirstName";
    private static final String KEY_LastName = "LastName";
    private static final String KEY_Phone = "Phone";
    private static final String KEY_Email= "Email";
    private static final String KEY_Address = "Address";

    // Transaction Table
    private static final String TABLE_TRANSACTIONS = "Transactions";
    private static final String PKEY_transactionID = "TranactionID";
    private static final String KEY_cardID = "CarID";
    private static final String KEY_customerID = "CustomerID";
    private static final String KEY_startDate = "StartDate";
    private static final String KEY_endDate= "EndDate";
    private static final String KEY_totalCost = "TotalCost";
    private static final String KEY_paymentStatus = "PaymentStatus";

    // Staff Table
    private static final String TABLE_STAFF = "Staff";
    private static final String PKEY_StaffID = "StaffID";
    private static final String KEY_Name = "Name";
    private static final String KEY_Role = "Role";
    private static final String KEY_Username = "Username";
    private static final String KEY_Password= "Password";
   // private static final String KEY_totalCost = "TotalCost";

    public MySQLDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create TABLE " + TABLE_CARS + "
        db.execSQL("Create TABLE " + TABLE_CARS + "(" +
                PKEY_CarID + " Text primary key," +
                KEY_Make + " Text ," +
                KEY_Model + " Text, " +
                 KEY_Year +" Integer," +
                KEY_LicencePlate + " Text, " +
                KEY_DailyRate + " Decimal, " +
                KEY_AvailabilityStatus+ " Text " +
                ");");
        // insert data into cars

        //   db.execSQL("Create TABLE " + TABLE_CUSTOMERS + "
        db.execSQL("Create TABLE " + TABLE_CUSTOMERS + "(" +
                PKEY_CustomerID + " Text primary key," +
                KEY_FirstName + " Text ," +
                KEY_LastName + " Text, " +
                KEY_Phone +" Text," +
                KEY_Email +" Text," +
                KEY_Address + " Text " +
                ");");

     //   Create Transaction table
        db.execSQL("Create TABLE " + TABLE_TRANSACTIONS + "(" +
                PKEY_transactionID + " Text primary key," +
                KEY_cardID + " Text ," +
                KEY_customerID + " Text," +
                KEY_startDate + " Date ," +
                KEY_endDate + " Date, " +
                KEY_totalCost +" Double," +
                KEY_paymentStatus +" Text," +
                "foreign key ("+ KEY_cardID + ") references Cars("+ PKEY_CarID+"),"+
                "foreign key ("+ KEY_customerID + ") references Customers("+ PKEY_CustomerID+")"+
                ");");

        //   Create Transaction Table
        db.execSQL("Create TABLE IF NOT EXISTS " + TABLE_STAFF + "(" +
                PKEY_StaffID + " Text primary key," +
                KEY_Name + " Text," +
                KEY_Role + " Text, " +
                KEY_Username +" Text," +
                KEY_Password +" Text " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // to drop table
  //        db.execSQL("Drop table if exists "+TABLE_CARS);
//        db.execSQL("Drop table if exists "+TABLE_CUSTOMERS);
  //      db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
  //      db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
 //       db.execSQL("DROP TABLE IF EXISTS " + TABLE_RENTALS);
   //     onCreate(db);
  //      onCreate(db);
        if (oldVersion < 2) {
            // Create the Staff table if upgrading from version 1 to 2
            //   Create Transaction Table
            db.execSQL("Create TABLE IF NOT EXISTS " + TABLE_STAFF + "(" +
                    PKEY_StaffID + " Text primary key," +
                    KEY_Name + " Text," +
                    KEY_Role + " Text, " +
                    KEY_Username +" Text," +
                    KEY_Password +" Text " +
                    ");");
        }

        // Additional schema changes can be handled here for future versions
    }
    public long insertIntoCars(String carId, String make, String model, int year, String licencePlate,
                               double dailyRate, String availabilityStatus){
        SQLiteDatabase db = null;
        long result = -1;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PKEY_CarID, carId);
            values.put(KEY_Make, make);
            values.put(KEY_Model, model);
            values.put(KEY_Year, year);
            values.put(KEY_LicencePlate, licencePlate);
            values.put(KEY_DailyRate, dailyRate);
            values.put(KEY_AvailabilityStatus, availabilityStatus);
             result =  db.insert(TABLE_CARS, null, values);
        }  finally {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
        return result;
    }


    public long insertIntoCustomers(String customerID,String firstNAme, String lastName, String phone,
                                    String email, String address){
        SQLiteDatabase db = null;
        long result = -1;
        try {
         db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PKEY_CustomerID, customerID);
        values.put(KEY_FirstName, firstNAme);
        values.put(KEY_LastName, lastName);
        values.put(KEY_Phone, phone);
        values.put(KEY_Email, email);
        values.put(KEY_Address, address);
        result = db.insert(TABLE_CUSTOMERS, null, values);
        }  finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }

    public long insertIntoTransactions(String transactionID, String carID, String customerID, Date startDate,
                                       Date endDate, Double totalCost, String paymentStatus){
        SQLiteDatabase db = null;
        long result = -1;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PKEY_transactionID, transactionID);
            values.put(KEY_cardID, carID);
            values.put(KEY_customerID, customerID);
            values.put(KEY_startDate, startDate.toString());
            values.put(KEY_endDate, endDate.toString());
            values.put(KEY_totalCost, totalCost);
            values.put(KEY_paymentStatus,paymentStatus);
            result = db.insert(TABLE_TRANSACTIONS, null, values);
        }  finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }
    public long insertIntoStaff(String staffID, String name, String role, String username,
                                    String password){
        SQLiteDatabase db = null;
        long result = -1;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(PKEY_StaffID, staffID);
            values.put(KEY_Name, name);
            values.put(KEY_Role, role);
            values.put(KEY_Username, username);
            values.put(KEY_Password, password);
            result = db.insert(TABLE_STAFF, null, values);
        }  finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }



    public ArrayList<CarModel> fetchAvaialbleCars(){
        SQLiteDatabase db = this.getReadableDatabase();
        int i=0;
        Cursor data = db.rawQuery("Select * from " + TABLE_CARS+" where "+KEY_AvailabilityStatus +" = 'Available'", null);
        ArrayList<CarModel> arrCars = new ArrayList<>();

        while(data.moveToNext()){

            CarModel cm = new CarModel(data.getString(0),data.getString(1),
                    data.getString(2),data.getInt(3),
                    data.getString(4),data.getDouble(5),
                    data.getString(6),images[i++]);
          //  ++i;
            arrCars.add(cm);
        }
        data.close();
        return arrCars;
    }

    public ArrayList<CarModel> fetchBookedCars(){
        SQLiteDatabase db = this.getReadableDatabase();
        int i=images.length-1;
        Cursor data = db.rawQuery("Select * from " + TABLE_CARS+" where "+KEY_AvailabilityStatus +" = 'Booked'", null);
        ArrayList<CarModel> arrCars = new ArrayList<>();

        while(data.moveToNext()){

            CarModel cm = new CarModel(data.getString(0),data.getString(1),
                    data.getString(2),data.getInt(3),
                    data.getString(4),data.getDouble(5),
                    data.getString(6),images[i]);
            --i;
            arrCars.add(cm);
        }
        data.close();
        return arrCars;
    }

    public ArrayList<CustomerModel> fetchCustomers(){
        int image = R.drawable.baseline_account_circle_70;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("Select * from "+ TABLE_CUSTOMERS, null);
        ArrayList<CustomerModel> arrCustomers = new ArrayList<>();
   //    int i=0;
        while(data.moveToNext()){
            CustomerModel cm = new CustomerModel(image, data.getString(0),data.getString(1),
                    data.getString(2),data.getString(3),
                    data.getString(4),data.getString(5));
            arrCustomers.add(cm);
        }
        data.close();
        return arrCustomers;
    }

    public ArrayList<TransactionModel> fetchTransactions(){
     //   int image = R.drawable.baseline_account_circle_70;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("Select * from "+ TABLE_TRANSACTIONS, null);
     //   Cursor date = db.
        ArrayList<TransactionModel> arrTransactions = new ArrayList<>();
        //    int i=0;
        while(data.moveToNext()){
            TransactionModel tm = new TransactionModel(data.getString(0),data.getString(1),
                    data.getString(2),new Date(data.getLong(3)),
                    new Date(data.getLong(4)),data.getDouble(5),data.getString(6));
            arrTransactions.add(tm);
        }
        data.close();
        return arrTransactions;
    }

    public ArrayList<StaffModel> fetchStaff(){
        int image = R.drawable.baseline_account_circle_70;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("Select * from "+ TABLE_STAFF, null);
        ArrayList<StaffModel> arrStaff = new ArrayList<>();
        //    int i=0;
        while(data.moveToNext()){
            StaffModel sm = new StaffModel(data.getString(0),data.getString(1),
                    data.getString(2),data.getString(3),
                    data.getString(4));
            arrStaff.add(sm);
        }
        data.close();
        return arrStaff;
    }

    public boolean checkLoginValidity(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STAFF, new String[] {KEY_Username, KEY_Password},
                                  KEY_Username +" = ? AND "+KEY_Password+" = ?",
                                   new String[] { username, password }, null, null, null);
        if(cursor.getCount() > 0){
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }
    int i = 11;
    public long registerUser(String name, String username, String password){
        SQLiteDatabase db = null;

        long result = -1;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(PKEY_StaffID, "s00"+(i));
            values.put(KEY_Name, name);
            values.put(KEY_Role, "GuestUser");
            values.put(KEY_Username, username);
            values.put(KEY_Password, password);

            result = db.insert(TABLE_STAFF, null, values);
            ++i;
        }  finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return result;
    }

    public ArrayList<CarModel> searchInCars(String query){
        ArrayList<CarModel> searchedCarList = new ArrayList<>();
        int i=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CARS, new String[]{PKEY_CarID,KEY_Make,KEY_Model,
                        KEY_Year,KEY_LicencePlate,KEY_DailyRate,KEY_AvailabilityStatus},
                "Make LIKE ? OR Model LIKE ? OR Year Like ? OR LicencePlate Like ? OR AvailabilityStatus Like ?",
                new String[]{"%" + query + "%","%" + query + "%","%" + query + "%","%" + query + "%","%" + query + "%"},
                null,null,null);
        while(cursor.moveToNext()){
            CarModel cm = new CarModel(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getInt(3),cursor.getString(4),
                    cursor.getDouble(5),cursor.getString(6),images[i]);
            searchedCarList.add(cm);
            ++i;
        }
        cursor.close();
        db.close();
        return searchedCarList;
    }
    public void updateAvailCar(String id,String make, String model, int year,double dailyRate ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Make, make);
        values.put(KEY_Model, model);
        values.put(KEY_DailyRate, dailyRate);
        db.update(TABLE_CARS,values, " CarID = ?", new String[]{id});
        db.close();
    }
}
