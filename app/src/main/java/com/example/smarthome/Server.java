package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Random;

public class Server extends AppCompatActivity {

    TextView totalA1,totalA2,totalU1,totalU2,confirmationCode,Max1,Max2;
    Button save1,save2;
    EditText iA,fA;
    String autoi,autoi1,on1,off1,dateString,dateString1,on2,off2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        totalA1 = findViewById(R.id.issaAmperage);
        totalA2 = findViewById(R.id.fatenAmperage);
        totalU1 = findViewById(R.id.issaUsage);
        totalU2 = findViewById(R.id.fatenUsage);
        confirmationCode = findViewById(R.id.confirmationCode);
        Max1 = findViewById(R.id.iMax);
        Max2 = findViewById(R.id.fMax);

        save1 = findViewById(R.id.button);
        save2 = findViewById(R.id.button2);

        iA = findViewById(R.id.iAm);
        fA = findViewById(R.id.fAm);


        generat();


        b2UwI0ZhnpaH9GeHNRlmZMTQyd22();
        EJFsPQlwGOS2YBZYl5gw6wHF6xx2();




    }
    public void EJFsPQlwGOS2YBZYl5gw6wHF6xx2(){

        EJFsPQlwGOS2YBZYl5gw6wHF6xx2 customer1 = new EJFsPQlwGOS2YBZYl5gw6wHF6xx2();

        customer1.kidsBedRoomSocket();
        customer1.kidsBedRoomLight();
        customer1.totalUsageKidsBedRoom();
        customer1.kidsBedRoomAmperage();

        customer1.masterBedRoomDimLight();
        customer1.masterBedRoomEntranceLight();
        customer1.masterBedRoomSpotLight();
        customer1.masterBedRoomMainLight();
        customer1.masterBedRoomSocket1();
        customer1.masterBedRoomSocket2();
        customer1.totalUsageMasterBedRoom();
        customer1.masterBedRoomAmperage();

        customer1.salonDimLight();
        customer1.salonEntranceLight();
        customer1.salonSpotLight();
        customer1.salonMainLight();
        customer1.salonSocket1();
        customer1.salonSocket2();
        customer1.totalUsageSalon();
        customer1.salonAmperage();

        customer1.kitchenFridgeSocket();
        customer1.kitchenLightSwitch();
        customer1.kitchenMicrowaveSocket();
        customer1.kitchenSocket1();
        customer1.kitchenSocket2();
        customer1.kitchenSocket3();
        customer1.totalUsageKitchen();
        customer1.kitchenAmperage();

        customer1.hotWater();
        customer1.hotWaterTotalAmperage();

        customer1.kidsBedRoomAc();
        customer1.salonAc();
        customer1.masterBedRoomAc();
        customer1.totalUsageAc();
        customer1.totalAmperageAc();

        customer1.totalUsage();
        customer1.totalAmperage();

        //customer1.amperageCheck();
        customer1.maxAmperage();
        customer1.getAmperageSensors();

        final String uid = "EJFsPQlwGOS2YBZYl5gw6wHF6xx2";
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database1.getReference("Server").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalAmperage").getValue().toString();
                totalA2.setText(value);
                totalA2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database2.getReference("Server").child(uid);

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalUsage").getValue().toString();
                totalU2.setText(value);
                totalU2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child(uid);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MaxAmperage").getValue().toString();
                Max2.setText(value);
                Max2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String iAa = fA.getText().toString();
                FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MaxAmperage").setValue(iAa);
                fA.setText("");

            }
        });
        FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference4 = database4.getReference("Customers").child(uid);

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                autoi1 = dataSnapshot.child("auto").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        FirebaseDatabase database10 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference10 = database10.getReference("Customers").child(uid).child("GoAutoSetting")
                .child("HotWaterHeater");

        databaseReference10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                on2 = dataSnapshot.child("on").getValue().toString();
                off2 = dataSnapshot.child("off").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                dateString1 = sdf.format(date);

                                final String dt = dateString1.substring(12, 23);

                                if(dt.equals(on2)&&autoi1.equals("on")){
                                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("HotWater").child("status").setValue("on");
                                }
                                else if(dt.equals(off2)){
                                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("HotWater").child("status").setValue("off");
                                }


                            }

                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();




    }


    public void b2UwI0ZhnpaH9GeHNRlmZMTQyd22(){

        b2UwI0ZhnpaH9GeHNRlmZMTQyd22 customer1 = new b2UwI0ZhnpaH9GeHNRlmZMTQyd22();

        customer1.kidsBedRoomSocket();
        customer1.kidsBedRoomLight();
        customer1.totalUsageKidsBedRoom();
        customer1.kidsBedRoomAmperage();

        customer1.masterBedRoomDimLight();
        customer1.masterBedRoomEntranceLight();
        customer1.masterBedRoomSpotLight();
        customer1.masterBedRoomMainLight();
        customer1.masterBedRoomSocket1();
        customer1.masterBedRoomSocket2();
        customer1.totalUsageMasterBedRoom();
        customer1.masterBedRoomAmperage();

        customer1.salonDimLight();
        customer1.salonEntranceLight();
        customer1.salonSpotLight();
        customer1.salonMainLight();
        customer1.salonSocket1();
        customer1.salonSocket2();
        customer1.totalUsageSalon();
        customer1.salonAmperage();

        customer1.kitchenFridgeSocket();
        customer1.kitchenLightSwitch();
        customer1.kitchenMicrowaveSocket();
        customer1.kitchenSocket1();
        customer1.kitchenSocket2();
        customer1.kitchenSocket3();
        customer1.totalUsageKitchen();
        customer1.kitchenAmperage();

        customer1.hotWater();
        customer1.hotWaterTotalAmperage();

        customer1.kidsBedRoomAc();
        customer1.salonAc();
        customer1.masterBedRoomAc();
        customer1.totalUsageAc();
        customer1.totalAmperageAc();

        customer1.totalUsage();
        customer1.totalAmperage();

        //customer1.amperageCheck();
        customer1.maxAmperage();
        customer1.getAmperageSensors();

        final String uid = "b2UwI0ZhnpaH9GeHNRlmZMTQyd22";
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database1.getReference("Server").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalAmperage").getValue().toString();
                totalA1.setText(value);
                totalA1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database2.getReference("Server").child(uid);

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalUsage").getValue().toString();
                totalU1.setText(value);
                totalU1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child(uid);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MaxAmperage").getValue().toString();
                Max1.setText(value);
                Max1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String iAa = iA.getText().toString();
                FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MaxAmperage").setValue(iAa);
                iA.setText("");

            }
        });
        FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference4 = database4.getReference("Customers").child(uid);

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                autoi = dataSnapshot.child("auto").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        FirebaseDatabase database10 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference10 = database10.getReference("Customers").child(uid).child("GoAutoSetting")
                .child("HotWaterHeater");

        databaseReference10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                on1 = dataSnapshot.child("on").getValue().toString();
                off1 = dataSnapshot.child("off").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    long date = System.currentTimeMillis();
                                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                    dateString = sdf.format(date);

                                    final String dt = dateString.substring(12, 23);

                                    if(dt.equals(on1)&&autoi.equals("on")){
                                        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("HotWater").child("status").setValue("on");
                                    }
                                    else if(dt.equals(off1)){
                                        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("HotWater").child("status").setValue("off");
                                    }


                                }

                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };
            t.start();




        }



    private void generat() {


        Thread t = new Thread(){

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Random ran = new Random();
                            Thread.sleep(20000);
                            int number = ran.nextInt(10000)+1;
                        FirebaseDatabase.getInstance().getReference("Server")
                                .child("ConfirmationCode").setValue(number);
                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference1 = database2.getReference("Server");

                        databaseReference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value = dataSnapshot.child("ConfirmationCode").getValue().toString();
                                confirmationCode.setText(value);
                                confirmationCode.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }catch (InterruptedException e){

                }
            }

        };
        t.start();

    }


}


