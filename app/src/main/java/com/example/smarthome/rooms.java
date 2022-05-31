package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class rooms extends AppCompatActivity {

    Button masterBedRoom,kidsBedRoom,salon,kitchen;
    String uid;
    Switch hotWater,mainBreaker;
    double hw,CurrentAm,MaxAm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);


        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        getAmperageSensors();
        getCurrentData();



        masterBedRoom = findViewById(R.id.masterBedRoom);
        kidsBedRoom = findViewById(R.id.kidsBedRoom);
        kitchen = findViewById(R.id.kitchen);

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKitchen();
            }
        });
        masterBedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMasterBedRoom();
            }
        });
        salon = findViewById(R.id.salon);
        salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSalon();
            }
        });

        kidsBedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKidsBedRoom();
            }
        });
        mainBreaker = findViewById(R.id.mainBreaker);
        mainBreaker();
        mainBreaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    final String status="on";
                    OnOff onOff = new OnOff(status);

                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("MainBreaker").setValue(onOff);
                }
                else {
                    final String status="off";
                    OnOff onOff = new OnOff(status);

                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("MainBreaker").setValue(onOff);

                }

            }
        });

        hotWater();
        hotWater = findViewById(R.id.hotwater);

        hotWater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new Background_get().execute("led1=1");
                    final String status = "on";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("HotWater").setValue(onOff);
                    CurrentAm += hw;
                } else if (CurrentAm > MaxAm) {
                    Toast.makeText(rooms.this, "You Can't", Toast.LENGTH_LONG).show();
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("HotWater")
                            .child("status").setValue("off");
                } else {
                    new Background_get().execute("led1=0");
                    final String status = "off";
                    OnOff onOff = new OnOff(status);



                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("HotWater").setValue(onOff);

                }
            }
        });

    }


    private void mainBreaker() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("MainBreaker");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainBreaker.setChecked(true);
                    hotWater.setEnabled(true);

                }
                else {
                    mainBreaker.setChecked(false);
                    hotWater.setEnabled(false);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("HotWater").child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("MainBreaker").child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("MainBreaker").child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("MainBreaker").child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("MasterBedRoom").child("MainBreaker").child("status").setValue("off");
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainBreaker").child("status").setValue("off");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void hotWater() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("HotWater");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                final int duration = Toast.LENGTH_SHORT;
                if(value.equals("on")){
                    hotWater.setChecked(true);




                }
                else {
                    hotWater.setChecked(false);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void openKitchen() {
        Intent intent= new Intent(this,kitchen.class);
        intent.putExtra("message", uid);
        startActivity(intent);
    }

    private void openSalon() {
        Intent intent= new Intent(this,salon.class);
        intent.putExtra("message", uid);
        startActivity(intent);
    }

    private void openKidsBedRoom() {

        Intent intent= new Intent(this,kidsBedRoom.class);
        intent.putExtra("message", uid);
        startActivity(intent);
    }

    private void openMasterBedRoom() {

        Intent intent= new Intent(this,masterBedRoom.class);
        intent.putExtra("message", uid);
        startActivity(intent);

    }
    public void getAmperageSensors(){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child("AmperageSensors");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String hotWater = dataSnapshot.child("hotWater").getValue().toString();
                hw = Double.parseDouble(hotWater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    private void getCurrentData() {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database1.getReference("Server").child(uid);

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalAmperage").getValue().toString();
                CurrentAm = Double.parseDouble(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Server").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MaxAmperage").getValue().toString();
                MaxAm = Integer.parseInt(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                /* Change the IP to the IP you set in the arduino sketch */
                URL url = new URL("http://192.168.0.108/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
