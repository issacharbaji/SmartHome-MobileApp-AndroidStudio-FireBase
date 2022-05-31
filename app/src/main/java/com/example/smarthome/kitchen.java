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

public class kitchen extends AppCompatActivity {

    Switch fridgeSocket, lightSwitch, microwaveSocket,socket1,socket2,mainBreakerSwitch, socket3;
    DatabaseReference databaseReference;
    Button home,goBack;
    String uid;
    double fr,so,li,mi,CurrentAm,MaxAm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        fridgeSocket = findViewById(R.id.fridgeSocket);
        lightSwitch = findViewById(R.id.lightSwitch);
        socket3 = findViewById(R.id.socket3);
        microwaveSocket = findViewById(R.id.microwaveSocket);
        socket1 = findViewById(R.id.socket1);
        socket2 = findViewById(R.id.socket2);
        mainBreakerSwitch = findViewById(R.id.mainBreaker);




        mainBreakerSwitch();
        fridgeSocket();
        socket1();
        socket2();
        socket3();
        microwaveSocket();
        lightSwitch();
        getAmperageSensors();
        getCurrentData();


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
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("MainBreaker").setValue(onOff);


                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("MainBreaker").setValue(onOff);



                }
            }
        });
        fridgeSocket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("fridgeSocket").setValue(onOff);
                    CurrentAm+=fr;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("fridgeSocket")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("fridgeSocket").setValue(onOff);
                }

            }
        });

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("lightSwitch").setValue(onOff);
                    CurrentAm+=li;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("lightSwitch")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("lightSwitch").setValue(onOff);
                }
            }
        });

        microwaveSocket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("microwaveSocket").setValue(onOff);
                    CurrentAm+=mi;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("microwaveSocket")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("microwaveSocket").setValue(onOff);
                }
            }
        });

        socket3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket3").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket3")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }


                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket3").setValue(onOff);
                }


            }
        });

        socket1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket1").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket1")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket1").setValue(onOff);
                }


            }
        });

        socket2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    final String status="on";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket2").setValue(onOff);
                    CurrentAm+=so;
                    if(CurrentAm>MaxAm){
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket2")
                                .child("status").setValue("off");
                        Toast.makeText(kitchen.this, "You Can't", Toast.LENGTH_LONG).show();

                    }

                }
                else {
                    final String status="off";

                    OnOff onOff = new OnOff(status);
                    FirebaseDatabase.getInstance().getReference().child("Customers").child(uid).child("Kitchen").child("socket2").setValue(onOff);
                }


            }
        });


    }

    private void goBack() {

        Intent intent = new Intent(kitchen.this,rooms.class);
        startActivity(intent);

    }

    private void openHome() {

        Intent intent = new Intent(kitchen.this,main.class);
        startActivity(intent);


    }


    private void mainBreakerSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("MainBreaker");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    mainBreakerSwitch.setChecked(true);
                    fridgeSocket.setEnabled(true);
                    microwaveSocket.setEnabled(true);
                    lightSwitch.setEnabled(true);
                    socket3.setEnabled(true);
                    socket1.setEnabled(true);
                    socket2.setEnabled(true);
                }
                else {
                    mainBreakerSwitch.setChecked(false);

                    fridgeSocket.setChecked(false);
                    microwaveSocket.setChecked(false);
                    lightSwitch.setChecked(false);
                    socket3.setChecked(false);
                    socket1.setChecked(false);
                    socket2.setChecked(false);

                    fridgeSocket.setEnabled(false);
                    microwaveSocket.setEnabled(false);
                    lightSwitch.setEnabled(false);
                    socket3.setEnabled(false);
                    socket1.setEnabled(false);
                    socket2.setEnabled(false);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void fridgeSocket() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("fridgeSocket");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    fridgeSocket.setChecked(true);
                }
                else {
                    fridgeSocket.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void microwaveSocket() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("microwaveSocket");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    microwaveSocket.setChecked(true);

                }
                else {
                    microwaveSocket.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void lightSwitch() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("lightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    lightSwitch.setChecked(true);
                }
                else {
                    lightSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void socket1() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("socket1");

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
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("socket2");

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
    private void socket3() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("socket3");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();
                if(value.equals("on")){
                    socket3.setChecked(true);
                }
                else {
                    socket3.setChecked(false);
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

                String fridge = dataSnapshot.child("fridge").getValue().toString();

                String light = dataSnapshot.child("light").getValue().toString();
                String microwave = dataSnapshot.child("microwave").getValue().toString();
                String socket = dataSnapshot.child("socket").getValue().toString();


                fr = Double.parseDouble(fridge);
                li = Double.parseDouble(light);
                mi = Double.parseDouble(microwave);
                so = Double.parseDouble(socket);

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

}
