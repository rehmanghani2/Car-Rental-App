package com.app.carrentalapp.Database;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTask extends AsyncTask<Void, Void, Connection> {


    @Override
    protected Connection doInBackground(Void... params) {
        // Perform the database connection in the background thread
        try {
            return getConnection();  // Your method to establish a database connection
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Connection connection) {
        // Handle the result after the background task is completed
        if (connection != null) {
            // Connection successful
        } else {
            // Handle failure
        }
    }


    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Return connection
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carrentalproject", "root", "@hm@dz@i123");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}

