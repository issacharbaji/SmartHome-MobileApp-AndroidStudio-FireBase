package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class kidsBedRoom extends AppCompatActivity {

    String uid;
    Switch mainBreaker,light,socket;
    Button home;
    double li,so,MaxAm,CurrentAm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_bed_room);


        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        mainBreaker = findViewById(R.id.mainBreaker);
        light = findViewById(R.id.light);
        socket = findViewById(R.id.socket);

        mainBreaker();
        light();
        socket();
        getAmperageSensors();
        getCurrentData();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("MainBreaker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("off")) {
                    mainBreaker.setEnabled(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mainBreaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("MainBreaker").setValue(onOff);

                }
                else {
                    final String status="off";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("MainBreaker").setValue(onOff);

                }

            }
        });

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    final String status="on";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("light").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("light")
                                .child("status").setValue("off");
                        Toast.makeText(kidsBedRoom.this, "You Can't", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    final String status="off";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("light").setValue(onOff);
                }
            }
        });

        socket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    final String status="on";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("socket").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("socket")
                                .child("status").setValue("off");
                        Toast.makeText(kidsBedRoom.this, "You Can't", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    final String status="off";
                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("KidsBedRoom").child("socket").setValue(onOff);
                }
            }
        });


    }

    private void socket() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("KidsBedRoom").child("socket");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                   socket.setChecked(true);


                }
                else {
                    socket.setChecked(false);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void light() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("KidsBedRoom").child("light");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    light.setChecked(true);
                }
                else {
                    light.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void mainBreaker() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("KidsBedRoom").child("MainBreaker");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainBreaker.setChecked(true);
                    light.setEnabled(true);
                    socket.setEnabled(true);
                }
                else {
                    mainBreaker.setChecked(false);

                    light.setEnabled(false);
                    socket.setEnabled(false);
                    light.setChecked(false);
                    socket.setChecked(false);

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
