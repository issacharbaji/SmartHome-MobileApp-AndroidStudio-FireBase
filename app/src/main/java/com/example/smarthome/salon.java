package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class salon extends AppCompatActivity {

    Switch entranceLightSwitch,mainLightSwitch,spotsLightSwitch,socket1,socket2,mainBreakerSwitch,dimLightSwitch;
    DatabaseReference databaseReference;
    Button home,goBack;
    String uid;
    double MaxAm,CurrentAm,li,so;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");


        entranceLightSwitch = findViewById(R.id.fridgeSocket);
        mainLightSwitch = findViewById(R.id.lightSwitch);
        dimLightSwitch = findViewById(R.id.socket3);
        spotsLightSwitch = findViewById(R.id.microwaveSocket);
        socket1 = findViewById(R.id.socket1);
        socket2 = findViewById(R.id.socket2);
        mainBreakerSwitch = findViewById(R.id.mainBreaker);


        entranceLightSwitch();
        mainLightSwitch();
        spotsLightSwitch();
        dimLightSwitch();
        socket1();
        socket2();
        mainBreakerSwitch();
        getCurrentData();
        getAmperageSensors();



        home = findViewById(R.id.home);
        goBack = findViewById(R.id.goback);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MainBreaker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("off")){
                    mainBreakerSwitch.setEnabled(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        mainBreakerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainBreaker").setValue(onOff);


                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainBreaker").setValue(onOff);


                }
            }
        });
        entranceLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("EntranceLightSwitch").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("EntranceLightSwitch")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("EntranceLightSwitch").setValue(onOff);
                }

            }
        });

        mainLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainLightSwitch").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainLightSwitch")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("MainLightSwitch").setValue(onOff);
                }
            }
        });

        spotsLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("SpotsLightSwitch").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("SpotsLightSwitch")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("SpotsLightSwitch").setValue(onOff);
                }
            }
        });

        dimLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("DimLightSwitch").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("DimLightSwitch")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("DimLightSwitch").setValue(onOff);
                }


            }
        });

        socket1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket1").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket1")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket1").setValue(onOff);
                }


            }
        });

        socket2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket2").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket2")
                                .child("status").setValue("off");
                        Toast.makeText(salon.this, "You Can't", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("salon").child("socket2").setValue(onOff);
                }


            }
        });
    }
    private void goBack() {

        Intent intent = new Intent(salon.this,rooms.class);
        startActivity(intent);

    }

    private void openHome() {

        Intent intent = new Intent(salon.this,main.class);
        startActivity(intent);


    }


    private void mainBreakerSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("MainBreaker");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainBreakerSwitch.setChecked(true);
                    entranceLightSwitch.setEnabled(true);
                    spotsLightSwitch.setEnabled(true);
                    mainLightSwitch.setEnabled(true);
                    dimLightSwitch.setEnabled(true);
                    socket1.setEnabled(true);
                    socket2.setEnabled(true);
                }
                else {
                    mainBreakerSwitch.setChecked(false);

                    entranceLightSwitch.setEnabled(false);
                    spotsLightSwitch.setEnabled(false);
                    mainLightSwitch.setEnabled(false);
                    dimLightSwitch.setEnabled(false);
                    socket1.setEnabled(false);
                    socket2.setEnabled(false);

                    entranceLightSwitch.setChecked(false);
                    spotsLightSwitch.setChecked(false);
                    mainLightSwitch.setChecked(false);
                    dimLightSwitch.setChecked(false);
                    socket1.setChecked(false);
                    socket2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void entranceLightSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("EntranceLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    entranceLightSwitch.setChecked(true);
                }
                else {
                    entranceLightSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void spotsLightSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("SpotsLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    spotsLightSwitch.setChecked(true);
                }
                else {
                    spotsLightSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void mainLightSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("MainLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainLightSwitch.setChecked(true);
                }
                else {
                    mainLightSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void dimLightSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("DimLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    dimLightSwitch.setChecked(true);
                }
                else {
                    dimLightSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void socket1() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("socket1");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    socket1.setChecked(true);
                }
                else {
                    socket1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void socket2() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon").child("socket2");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    socket2.setChecked(true);
                }
                else {
                    socket2.setChecked(false);
                }
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
    public void getAmperageSensors(){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child("AmperageSensors");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String light = dataSnapshot.child("light").getValue().toString();
                String socket = dataSnapshot.child("socket").getValue().toString();

                li = Double.parseDouble(light);
                so = Double.parseDouble(socket);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
