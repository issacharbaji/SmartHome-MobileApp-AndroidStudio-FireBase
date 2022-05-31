package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {

    Button gm,gn,gout,gauto;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        gm = findViewById(R.id.gm);
        gn = findViewById(R.id.gn);
        gout = findViewById(R.id.gout);
        gauto = findViewById(R.id.gauto);
        
        gout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoOut();
            }
        });
        gauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoAuto();
            }
        });



    }

    private void openGoAuto() {

        Intent intent = new Intent(this,goAuto.class);
        intent.putExtra("message", uid);
        startActivity(intent);
    }

    private void openGoOut() {

        Intent intent = new Intent(this,goOut.class);
        intent.putExtra("message", uid);
        startActivity(intent);
    }
}
