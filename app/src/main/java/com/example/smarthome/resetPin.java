package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class resetPin extends AppCompatActivity {

    String message;
    EditText newPin,pinCon;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        newPin = findViewById(R.id.nP);
        pinCon = findViewById(R.id.cP);

        save = findViewById(R.id.save2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newP = newPin.getText().toString();
                final String passCon = pinCon.getText().toString();

                if( newP.equals("") || passCon.equals("")){
                    Toast.makeText(resetPin.this, "Some Fields are Empty!", Toast.LENGTH_LONG).show();

                }
                else {

                    if (newP.equals(passCon)) {
                        FirebaseDatabase.getInstance().getReference().child("Customers").child(message).child("CustomerInfo").child("pin").setValue(passCon);
                        Toast.makeText(resetPin.this, "Pin Changed", Toast.LENGTH_LONG).show();
                        }
                    else {
                        Toast.makeText(resetPin.this, "Error", Toast.LENGTH_LONG).show();
                    }

                }



            }
        });
    }
}
