package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ac extends AppCompatActivity {

    Switch mainBreaker,kidsBedRoomAc,masterBedRoomAc,salonAc;
    String uid;
    double MaxAm,CurrentAm,ac1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);



        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        mainBreaker = findViewById(R.id.acMainBreaker);
        kidsBedRoomAc = findViewById(R.id.onoff2);
        salonAc = findViewById(R.id.onoff);
        masterBedRoomAc = findViewById(R.id.onoff1);

        masterBedRoomAc();
        salonAc();
        mainBreakerSwitch();
        kidsBedRoomAc();
        getCurrentData();
        getAmperageSensors();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("MainBreaker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("off")){
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
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("MainBreaker").setValue(onOff);

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("MainBreaker").setValue(onOff);


                }
            }
        });

        kidsBedRoomAc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("kidsRoomAc").setValue(onOff);
                    CurrentAm+=ac1;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("kidsRoomAc")
                                .child("status").setValue("off");
                        Toast.makeText(ac.this, "You Can't", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("kidsRoomAc").setValue(onOff);


                }
            }
        });
        masterBedRoomAc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("masterBedRoomAc").setValue(onOff);
                    CurrentAm+=ac1;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("masterBedRoomAc")
                                .child("status").setValue("off");
                        Toast.makeText(ac.this, "You Can't", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("masterBedRoomAc").setValue(onOff);


                }
            }
        });

        salonAc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("salonAc").setValue(onOff);
                    CurrentAm+=ac1;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("salonAc")
                                .child("status").setValue("off");
                        Toast.makeText(ac.this, "You Can't", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Ac").child("salonAc").setValue(onOff);


                }
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

    private void mainBreakerSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("Ac").child("MainBreaker");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainBreaker.setChecked(true);
                    salonAc.setEnabled(true);
                    kidsBedRoomAc.setEnabled(true);
                    masterBedRoomAc.setEnabled(true);

                }
                else {
                    mainBreaker.setChecked(false);

                    salonAc.setEnabled(false);
                    kidsBedRoomAc.setEnabled(false);
                    masterBedRoomAc.setEnabled(false);

                    salonAc.setChecked(false);
                    kidsBedRoomAc.setChecked(false);
                    masterBedRoomAc.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void salonAc() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("Ac").child("salonAc");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    salonAc.setChecked(true);
                }
                else {
                    salonAc.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void kidsBedRoomAc() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("Ac").child("kidsRoomAc");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    kidsBedRoomAc.setChecked(true);
                }
                else {
                    kidsBedRoomAc.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void masterBedRoomAc() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("Ac").child("masterBedRoomAc");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    masterBedRoomAc.setChecked(true);
                }
                else {
                   masterBedRoomAc.setChecked(false);
                }
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

                String hotWater = dataSnapshot.child("ac").getValue().toString();
                ac1 = Double.parseDouble(hotWater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
