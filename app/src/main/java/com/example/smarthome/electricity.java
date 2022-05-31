package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

public class electricity extends AppCompatActivity {


    String message,usage;
    Button request,history;
    String value2,last,dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);



        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        request = findViewById(R.id.request);
        history = findViewById(R.id.bill);

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
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a");
                                dateString = sdf.format(date);



                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        FirebaseDatabase database6 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference6 = database6.getReference("Server").child(message)
                .child("electricity");

        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value2 = dataSnapshot.child("Counting").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openHistory();

            }
        });



        final int[] i = new int[1];
        request.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                final int[] us = new int[1];
                final int[] la = new int[1];
                final int[] bill = new int[1];

                final int[] counting = new int[1];
                counting[0]=Integer.parseInt(value2);

                counting[0]++;
                final int[] counting1 = new int[1];
                counting1[0]=counting[0]-1;
                final String p = Integer.toString(counting[0]);
                final String p1 = Integer.toString(counting1[0]);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("Server").child(message);

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        usage = dataSnapshot.child("totalUsage").getValue().toString();
                        last = dataSnapshot.child("electricity").child(p1).child("CurrentConsumption").getValue().toString();
                        us[0]=Integer.parseInt(usage);

                        FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child(p)
                                .child("CurrentConsumption").setValue(usage);
                        FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child(p)
                                .child("lastConsumption").setValue(last);
                        la[0]=Integer.parseInt(last);
                        bill[0]=us[0]-la[0]+10000;
                        FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child("Counting")
                              .setValue(p);
                        Toast.makeText(electricity.this, "Your Bill is " + bill[0] + " LBP", Toast.LENGTH_LONG).show();
                        FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child(p)
                                .child("Bill").setValue(bill[0]);


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child(p)
                        .child("Date").setValue(dateString);



                //Toast.makeText(electricity.this, "Your Bill is " + bill[0] + " LBP", Toast.LENGTH_LONG).show();
                //FirebaseDatabase.getInstance().getReference("Server").child(message).child("electricity").child(p)
                        //.child("Bill").setValue(bill[0]);


            }

        });






    }

    private void CalculateBill() {


    }

    private void openHistory() {

        Intent intent = new Intent(this,history.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}
