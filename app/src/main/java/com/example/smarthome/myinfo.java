package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myinfo extends AppCompatActivity {

    String message;
    TextView name,mobile,email;
    Button resetPass,resetPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        name = findViewById(R.id.name1);
        email = findViewById(R.id.email1);
        mobile = findViewById(R.id.mobile1);

        resetPass = findViewById(R.id.resetPass);
        resetPin = findViewById(R.id.resetPin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(message).child("CustomerInfo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.child("name").getValue().toString();
                String value1= dataSnapshot.child("lastName").getValue().toString();
                String na = value + " " + value1;
                name.setText(na);
                String value2 = dataSnapshot.child("email").getValue().toString();
                email.setText(value2);
                String value3 = dataSnapshot.child("phone").getValue().toString();
                mobile.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResetPass();
            }
        });
        resetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResetPin();
            }
        });
    }

    private void openResetPin() {
        Intent intent = new Intent(this,resetPin.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    private void openResetPass() {
        Intent intent = new Intent(this,resetPass2.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}
