package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static String SAMPLE_INPUT = "0.932833333 0.003483333 0.932833333 0.0000333 0.12415 0.013083333 0.001516667 36.14 18000 0";

    private Handler handler;
    private TFLiteModel tfLiteModel;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(() -> tfLiteModel.load());
    }

    @Override
    protected void onStop() {
        handler.post(() -> tfLiteModel.unload());
        super.onStop();
    }



    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button Out,salon;
    TextView name,goOut,goauto1;
    Button securitySensors,mainGate,parking,auto,cameras;


    String message,value,value1,value2,auto1;
    String dateString,on,off,camoff;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        handler = new Handler();
        tfLiteModel = new TFLiteModel(getApplicationContext());


        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        Intent intent1 = new Intent(this,Server.class);
        intent1.putExtra("message", message);

        auto = findViewById(R.id.goauto);
        cameras = findViewById(R.id.cameras);
        goauto1 = findViewById(R.id.goautot);




        auto.setOnClickListener(new View.OnClickListener() {
            final int[] i = new int[1];
            @Override
            public void onClick(View v) {

                if(i[0] ==0 && auto1.equals("off")) {

                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("auto").setValue("on");
                    goauto1.setTextColor(Color.GREEN);
                    i[0]++;

                    predict(timeInSec());



                }
                else {
                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("auto").setValue("off");
                    goauto1.setTextColor(Color.RED);
                    i[0] =0;

                }

            }
        });



        goOut = findViewById(R.id.goOut);
        securitySensors=findViewById(R.id.securitySensors);

        parking = findViewById(R.id.parking);
        parking.setTextColor(Color.RED);

        mainGateParking();
        securitySensors();
        cameras();

        name=findViewById(R.id.name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(message).child("CustomerInfo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("name").getValue().toString();
                name.setText(value);
                name.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database1.getReference("Police").child(message);

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("SecuritySensors").getValue().toString();
                if(value.equals("off")){
                    goOut.setTextColor(Color.RED);
                }
                else{
                    goOut.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference3 = database3.getReference("Customers").child(message);

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                auto1 = dataSnapshot.child("auto").getValue().toString();
                if(auto1.equals("off")){
                    goauto1.setTextColor(Color.RED);
                }
                else{
                    goauto1.setTextColor(Color.GREEN);
                }
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
                                TextView tdate = (TextView) findViewById(R.id.dateTime);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                dateString = sdf.format(date);
                                tdate.setText(dateString);


                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();



        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigavion_view);
        navigationView.setNavigationItemSelectedListener(this);


        Out = findViewById(R.id.out);

        FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference5 = database5.getReference("Customers").child(message);

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value1 = dataSnapshot.child("Out").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database6 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference6 = database6.getReference("Police").child(message);

        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value2 = dataSnapshot.child("Counting").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final int[] i = new int[1];
        final int[] u = new int[1];

            Out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final int[] counting = new int[1];
                    counting[0]=Integer.parseInt(value2);
                    String p1 = Integer.toString(counting[0]);
                    if(i[0] ==0 && value1.equals("no")) {

                        counting[0]++;
                        FirebaseDatabase.getInstance().getReference("Police").child(message).child("Counting").setValue(counting[0]);
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        u[0]++;

                        String p = Integer.toString(counting[0]);
                        Toast.makeText(main.this, currentDateTimeString + " Out", Toast.LENGTH_LONG).show();
                        FirebaseDatabase.getInstance().getReference("Police").child(message).child("SecuritySensors").setValue("on");
                        FirebaseDatabase.getInstance().getReference("Police").child(message).child(p).child("Out").setValue(currentDateTimeString);
                        FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Out").setValue("yes");
                        goOut.setTextColor(Color.GREEN);
                        i[0]++;

                    }
                    else {

                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        FirebaseDatabase.getInstance().getReference("Police").child(message).child("SecuritySensors").setValue("off");
                        Toast.makeText(main.this, currentDateTimeString +" In", Toast.LENGTH_LONG).show();
                        FirebaseDatabase.getInstance().getReference("Police").child(message).child(p1).child("In").setValue(currentDateTimeString);
                        FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Out").setValue("no");
                        goOut.setTextColor(Color.RED);
                        i[0] =0;

                    }




                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference("Customers").child(message).child("GoOutSetting");

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
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("KidsBedRoom").child("light")
                                .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Kitchen").child("lightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("DimLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("EntranceLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("MainLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("SpotsLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("DimLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("EntranceLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("MainLightSwitch")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("SpotsLightSwitch")
                                        .child("status").setValue("off");
                            }
                            if(value1.equals("off")){
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("KidsBedRoom").child("socket")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Kitchen").child("socket1")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Kitchen").child("socket2")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Kitchen").child("socket3")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("socket1")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MasterBedRoom").child("socket2")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("socket1")
                                        .child("status").setValue("off");
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("salon").child("socket2")
                                        .child("status").setValue("off");

                            }
                            if(value2.equals("off")){
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Ac").child("kidsRoomAc")
                                        .child("status").setValue("off");

                            }
                            if(value3.equals("off")){
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Ac").child("masterBedRoomAc")
                                        .child("status").setValue("off");

                            }
                            if(value4.equals("off")){
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MainBreaker")
                                        .child("status").setValue("off");

                            }
                            if(value5.equals("off")){
                                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Ac").child("salonAc")
                                        .child("status").setValue("off");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });





                }
            });

        FirebaseDatabase database10 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference10 = database10.getReference("Customers").child(message).child("GoAutoSetting")
                .child("HotWaterHeater");

        databaseReference10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                on = dataSnapshot.child("on").getValue().toString();
                off = dataSnapshot.child("off").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void openTest() {


        Intent intent = new Intent(this,test.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }


    private void cameras() {
        cameras.setOnClickListener(new View.OnClickListener() {
            final int[] i = new int[1];

            @Override
            public void onClick(View v) {
                if(i[0] ==0 && camoff.equals("off")) {
                    cameras.setTextColor(Color.GREEN);
                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Cameras").setValue("on");
                    i[0]++;
                }
                else {
                    cameras.setTextColor(Color.RED);
                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Cameras").setValue("off");
                    i[0] =0;

                }

            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database2.getReference("Customers").child(message);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                camoff = dataSnapshot.child("Cameras").getValue().toString();
                if(camoff.equals("off")){
                    cameras.setTextColor(Color.RED);
                }
                else{
                    cameras.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void mainGateParking() {

        parking.setOnClickListener(new View.OnClickListener() {
            final int[] i2 = {0};
            @Override
            public void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {

                            while (i2[0] <4) {
                                Thread.sleep(15000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        parking.setTextColor(Color.RED);
                                        FirebaseDatabase.getInstance().getReference("Customers").child(message).child("ParkingGate").setValue("closed");
                                        i2[0]++;
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };
                t.start();
                i2[0]=0;
                parking.setTextColor(Color.GREEN);
                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("ParkingGate").setValue("open");
            }
        });

        final int[] i2 = {0};
        mainGate = findViewById(R.id.mainGate);
        mainGate.setTextColor(Color.RED);
        mainGate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {

                            while (i2[0] <4) {
                                Thread.sleep(5000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mainGate.setTextColor(Color.RED);
                                        FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MainGate").setValue("closed");
                                        i2[0]++;
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };
                t.start();
                i2[0]=0;
                mainGate.setTextColor(Color.GREEN);
                FirebaseDatabase.getInstance().getReference("Customers").child(message).child("MainGate").setValue("open");
            }
        });
    }

    private void securitySensors() {

        securitySensors.setOnClickListener(new View.OnClickListener() {
            final int[] i = new int[1];

            @Override
            public void onClick(View v) {
                if(i[0] ==0 && value.equals("off")) {
                    securitySensors.setTextColor(Color.GREEN);
                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("SecuritySensors").setValue("on");
                    i[0]++;
                }
                else {
                    securitySensors.setTextColor(Color.RED);
                    FirebaseDatabase.getInstance().getReference("Customers").child(message).child("SecuritySensors").setValue("off");
                    i[0] =0;

                }

            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database2.getReference("Customers").child(message);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.child("SecuritySensors").getValue().toString();
                if(value.equals("off")){
                    securitySensors.setTextColor(Color.RED);
                }
                else{
                    securitySensors.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void test1() {

        Intent intent= new Intent(this,test.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if ( id == R.id.home){


            Intent intent = new Intent(this,test.class);
            startActivity(intent);

        }
        if(id == R.id.rooms){

            Intent intent = new Intent(this,rooms.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
        if (id == R.id.ac){
            Intent intent = new Intent(this,ac.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }

        if( id == R.id.setting){

            Intent intent = new Intent(this,settings.class);
            intent.putExtra("message", message);
            startActivity(intent);

        }
        if( id == R.id.report){
            Intent intent = new Intent(this,report.class);
            intent.putExtra("message", message);
            startActivity(intent);

        }
        if( id == R.id.logout){

            Toast.makeText(this,"Stay Safe",Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        if( id == R.id.members){

            Intent intent = new Intent(this,members.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
        if( id == R.id.electricity){

            Intent intent = new Intent(this,electricity.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
        if( id == R.id.homeAppliances){

            Intent intent = new Intent(this,homeAppliances.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
        if( id == R.id.myInfo){

            Intent intent = new Intent(this,myinfo.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }

        return false;
    }
    private void predict(String input) {
        handler.post(() -> {
            String result = tfLiteModel.classify(SAMPLE_INPUT);
            showResult(result);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("Customers").child(message).child("Kitchen").child("microwaveSocket");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.child("status").getValue().toString();
                    if(result.equals("Off")&&value.equals("on")){
                        FirebaseDatabase.getInstance().getReference("Customers").child(message).child("Kitchen").child("microwaveSocket").child("status").setValue("off");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });
    }

    private void showResult(String result) {
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), "Result: " + result, Toast.LENGTH_LONG).show();
        });
    }

    private String timeInSec(){

        final String dt = dateString.substring(12, 23);
        int hr = Integer.parseInt(dt.substring(0,2));
        int min =Integer.parseInt(dt.substring(3,5));
        int sec = Integer.parseInt(dt.substring(6,8));
        int TimeInSeconds = hr*3600+min*60+sec;
        String time  = ""+TimeInSeconds;
                return time;
    }
}
