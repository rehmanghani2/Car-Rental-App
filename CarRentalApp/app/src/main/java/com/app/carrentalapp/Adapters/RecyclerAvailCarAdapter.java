package com.app.carrentalapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.carrentalapp.Activities.MainActivity;
import com.app.carrentalapp.Database.MySQLDB;
import com.app.carrentalapp.Models.CarModel;
import com.app.carrentalapp.R;

import java.util.ArrayList;

public class RecyclerAvailCarAdapter extends RecyclerView.Adapter<RecyclerAvailCarAdapter.ViewHolder> {
   Context context;
   ArrayList<CarModel> arrCars;
   public RecyclerAvailCarAdapter(Context context, ArrayList<CarModel> arrCars){
       this.context = context;
       this.arrCars = arrCars;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.avaialable_cars_row, parent, false);
       ViewHolder viewHolder= new ViewHolder(view);
       return viewHolder;

    }
    public void updateData(ArrayList<CarModel> arrCars){
       this.arrCars = arrCars;
       notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      // CarModel model = (CarModel) arrCars.get(position);
       holder.imgCarAvail.setImageResource(arrCars.get(position).img);
       holder.txtCarDailyRate.setText(String.valueOf(arrCars.get(position).dailyRate));
       holder.txtCarMake.setText(arrCars.get(position).make);
       holder.txtCarModel.setText(arrCars.get(position).model);
       holder.txtCarYear.setText(String.valueOf(arrCars.get(position).year));
       holder.txtCarStatus.setText(arrCars.get(position).availabilityStatus);
/*
       holder.availcarRow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Dialog dialog = new Dialog(context);
               dialog.setContentView(R.layout.update_car_layout);
             //  EditText id = dialog.findViewById(R.id.edtCarID);
               EditText make = dialog.findViewById(R.id.edtUpdCarMake);
               EditText model = dialog.findViewById(R.id.edtUpdCarModel);
               EditText year = dialog.findViewById(R.id.edtUpdCarYear);
              // EditText licencePlate = dialog.findViewById(R.id.edtCarLicPlate);
               EditText dailyRate = dialog.findViewById(R.id.edtUpdCarRate);
               //  EditText status = dialog.findViewById(R.id.edtCarStatus);
               Button btnAction = dialog.findViewById(R.id.carDialogBtn);

               // using layout for update task
//               TextView txtTitle = dialog.findViewById(R.id.carTxtTitle);
//               txtTitle.setText("Update Car");
//               btnAction.setText("Update");

             //  id.setText((arrCars.get(position)).carID);
               make.setText((arrCars.get(position)).make);
               model.setText((arrCars.get(position)).model);
               year.setText((arrCars.get(position)).year);
               dailyRate.setText(String.valueOf((arrCars.get(position)).dailyRate));


               btnAction.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String carId = (arrCars.get(position).carID);
                       String carMake="", carModel="";
                       int carYear=0;
                       double carDailyRate=0.0;

                       //       String carStatus = status.getText().toString();

                       if(!make.getText().toString().isEmpty()){
                           carMake = make.getText().toString();
                       }else {
                           Toast.makeText(context.getApplicationContext(), "Please Enter Car Make",Toast.LENGTH_SHORT).show();
                       }

                       if(!model.getText().toString().isEmpty()){
                           carModel = model.getText().toString();
                       }else {
                           Toast.makeText(context.getApplicationContext(), "Please Enter Car Model",Toast.LENGTH_SHORT).show();
                       }

                       if(!year.getText().toString().isEmpty()){
                           carYear = Integer.parseInt(year.getText().toString());
                       } else {
                           Toast.makeText(context.getApplicationContext(), "Please Enter Car Make Year",Toast.LENGTH_SHORT).show();
                       }

                       if(!dailyRate.getText().toString().isEmpty()){
                           carDailyRate = Double.parseDouble(dailyRate.getText().toString());
                       } else {
                           Toast.makeText(context.getApplicationContext(), "Please Enter Car Avaialbility Status",Toast.LENGTH_SHORT).show();
                       }

                       MySQLDB db = new MySQLDB(context.getApplicationContext());


                       arrCars.set(position, new CarModel(carMake, carModel,carYear,carDailyRate, R.drawable.image3  ));
                       notifyItemChanged(position);

                       db.updateAvailCar(carId,carMake,carModel,carYear,carDailyRate);
                       db.close();
                       dialog.dismiss();
                   }
               });
               dialog.show();

           }
       });*/

    }

    @Override
    public int getItemCount() {
        return arrCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView txtCarMake,txtCarModel, txtCarYear, txtCarDailyRate,txtCarStatus;
       ImageView imgCarAvail;
       LinearLayout availcarRow;
        public ViewHolder(View itemView){
            super(itemView);
            txtCarMake = itemView.findViewById(R.id.availCarMake);
            txtCarModel = itemView.findViewById(R.id.availCarModel);
            txtCarYear = itemView.findViewById(R.id.availCarYear);
            txtCarDailyRate = itemView.findViewById(R.id.availDailyRate);
            imgCarAvail = itemView.findViewById(R.id.availRowImg);
            txtCarStatus = itemView.findViewById(R.id.rowCarStatus);
            availcarRow = itemView.findViewById(R.id.availCarRow);
        }
    }
}
