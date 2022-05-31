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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button connected,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        connected = findViewById(R.id.start);
        register = findViewById(R.id.register);



        connected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogIN();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

    }






    private void openRegister() {

        Intent intent= new Intent(this,registration.class);
        startActivity(intent);
    }


    public void openLogIN(){

        Intent intent= new Intent(this,logIn.class);
        startActivity(intent);

    }


}
