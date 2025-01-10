package com.app.carrentalapp.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.CarrierConfigManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.carrentalapp.Activities.MainActivity;
import com.app.carrentalapp.Adapters.RecyclerAvailCarAdapter;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

/**

 */
public class AvailableCarsFragment extends Fragment {
    int[] images= {R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car,
            R.drawable.land_rover,R.drawable.prado,R.drawable.baseline_2014_toyota_highlander_hybrid_platinum,
            R.drawable.toyota_land_cruiser_200_grey, R.drawable.arden_range_rover2006,
            R.drawable.bmw,R.drawable.image5, R.drawable.brabus_800_mercedes_benz_g65_2014, R.drawable.car};
    ArrayList<CarModel> carList;
    private static  final String USER = "root";

    private static final  String URL = "jdbc:mysql://127.0.0.1:3306/?carrentalproject";
    private Connection sqlConnection;
    RecyclerView recyclerView;
    RecyclerAvailCarAdapter adapter;
    FloatingActionButton addCarDialog;

    public AvailableCarsFragment() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_cars, container, false);
        recyclerView = view.findViewById(R.id.homeRecyclerFragment);
        carList = new ArrayList<>();
        addCarDialog = view.findViewById(R.id.addInAvailCar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         MySQLDB database = new MySQLDB(getActivity());
        carList = database.fetchAvaialbleCars();
        database.close();

        RecyclerAvailCarAdapter adapter = new RecyclerAvailCarAdapter(getActivity(),carList);
        recyclerView.setAdapter(adapter);


        addCarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(requireActivity());
                dialog.setContentView(R.layout.add_update_car_layout);

                EditText id = dialog.findViewById(R.id.edtCarID);
                EditText make = dialog.findViewById(R.id.edtCarMake);
                EditText model = dialog.findViewById(R.id.edtCarModel);
                EditText year = dialog.findViewById(R.id.edtCarYear);
                EditText licencePlate = dialog.findViewById(R.id.edtCarLicPlate);
                EditText dailyRate = dialog.findViewById(R.id.edtCarRate);
              //  EditText status = dialog.findViewById(R.id.edtCarStatus);
                Button btnAction = dialog.findViewById(R.id.carDialogBtn);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         String carId="", carMake="", carModel="", carLicPlate = "";
                         int carYear=0;
                         double carDailyRate=0.0;

                 //       String carStatus = status.getText().toString();

                        if(id.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Please Enter Car ID(Primary Key)",Toast.LENGTH_SHORT).show();
                        } else {
                            carId = id.getText().toString();
                        }

                        if(!make.getText().toString().isEmpty()){
                            carMake = make.getText().toString();
                        }else {
                            Toast.makeText(getActivity(), "Please Enter Car Make",Toast.LENGTH_SHORT).show();
                        }

                        if(!model.getText().toString().isEmpty()){
                             carModel = model.getText().toString();
                        }else {
                            Toast.makeText(getActivity(), "Please Enter Car Model",Toast.LENGTH_SHORT).show();
                        }

                        if(!year.getText().toString().isEmpty()){
                            carYear = Integer.parseInt(year.getText().toString());
                        } else {
                            Toast.makeText(getActivity(), "Please Enter Car Make Year",Toast.LENGTH_SHORT).show();
                        }

                        if(!licencePlate.getText().toString().isEmpty()){
                             carLicPlate = licencePlate.getText().toString();
                        } else {
                            Toast.makeText(getActivity(), "Please Enter Car licence Plate",Toast.LENGTH_SHORT).show();
                        }

                        if(!dailyRate.getText().toString().isEmpty()){
                            carDailyRate = Double.parseDouble(dailyRate.getText().toString());
                        } else {
                            Toast.makeText(getActivity(), "Please Enter Car Avaialbility Status",Toast.LENGTH_SHORT).show();
                        }


                        carList.add(new CarModel(carId,carMake,carModel,carYear,carLicPlate,carDailyRate,"Available",R.drawable.prado));

                        adapter.notifyItemChanged(carList.size()-1);

                        recyclerView.scrollToPosition(carList.size()-1);

                        MySQLDB db = new MySQLDB(getActivity());
                        db.insertIntoCars(carId, carMake, carModel,carYear,carLicPlate,carDailyRate,"Avaialble");
                        carList = db.fetchAvaialbleCars();
                        db.close();

                        dialog.dismiss();
                    }
                });
                   dialog.show();
            }
        });


        return view;
    }
}



//    public ArrayList<CarModel> getAvailCars(){
//        int i =0;
//        ArrayList<CarModel> availCars = new ArrayList<>();
//        try(Connection sqlConnection = DriverManager.getConnection(URL, USER, PASS);
//            Statement stmt = sqlConnection.createStatement();
//            ResultSet rs = stmt.executeQuery("Select * from Cars where AvailabilityStatus ='Available' ")){
//            while(rs.next()){
//                CarModel car = new CarModel(rs.getString("CarID"),rs.getString("Make"),
//                        rs.getString("Model"),rs.getInt("Year"),
//                        rs.getString("LicencePlate"),rs.getDouble("DailyRate")*250,
//                        rs.getString("AvailabilityStatus"),images[i++]);
//                availCars.add(car);
//            }
//        } catch(SQLException se){
//            System.out.println("Error in getting cars from sql "+se.getMessage());
//        }
//        return availCars;
//    }
