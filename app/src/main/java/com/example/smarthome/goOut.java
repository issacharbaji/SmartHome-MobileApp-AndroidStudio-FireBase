package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class goOut extends AppCompatActivity {

    CheckBox kidsac,salonac,masterac,sockets,lights,main;
    String uid;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        kidsac = findViewById(R.id.kidsac);
        salonac = findViewById(R.id.salonac);
        masterac = findViewById(R.id.masterac);
        sockets = findViewById(R.id.sockets);
        lights = findViewById(R.id.lights);
        main = findViewById(R.id.main);

        final boolean[] kidAc = new boolean[1];
        final boolean[] salonAc = new boolean[1];
        final boolean[] masterAc = new boolean[1];
        final boolean[] Sockets = new boolean[1];
        final boolean[] Lights = new boolean[1];
        final boolean[] Main = new boolean[1];


        save = findViewById(R.id.save1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kidAc[0] = kidsac.isChecked();
                salonAc[0] = salonac.isChecked();
                masterAc[0] = masterac.isChecked();
                Sockets[0] = sockets.isChecked();
                Lights[0] = lights.isChecked();
                Main[0] = main.isChecked();

                if(kidAc[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("KidsBedRoomAc").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("KidsBedRoomAc").setValue("-");
                }
                if(salonAc[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("SalonAc").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("SalonAc").setValue("-");
                }
                if(masterAc[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MasterBedRoomAc").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MasterBedRoomAc").setValue("-");
                }
                if(Sockets[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllSockets").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllSockets").setValue("-");
                }
                if(Lights[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllLights").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllLights").setValue("-");
                }
                if(Main[0]){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MainBreaker").setValue("off");
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MainBreaker").setValue("-");
                }
                Toast.makeText(goOut.this, "Saved!", Toast.LENGTH_LONG).show();

            }

        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("GoOutSetting");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("AllLights").getValue().toString();
                String value1 = dataSnapshot.child("AllSockets").getValue().toString();
                String value2 = dataSnapshot.child("KidsBedRoomAc").getValue().toString();
                String value3 = dataSnapshot.child("MasterBedRoomAc").getValue().toString();
                String value4 = dataSnapshot.child("MainBreaker").getValue().toString();
                String value5 = dataSnapshot.child("SalonAc").getValue().toString();
                if(value.equals("off")){
                    lights.setChecked(true);
                }
                if(value1.equals("off")){
                    sockets.setChecked(true);
                }
                if(value2.equals("off")){
                    kidsac.setChecked(true);
                }
                if(value3.equals("off")){
                    masterac.setChecked(true);
                }
                if(value4.equals("off")){
                    main.setChecked(true);
                }
                if(value5.equals("off")){
                    salonac.setChecked(true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
