package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeAppliances extends AppCompatActivity {

    String message;
    Button his,send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_appliances);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        his = findViewById(R.id.request);
        send = findViewById(R.id.bill);

        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });

    }

    private void openHistory() {

        Intent intent = new Intent(this,reportHistory.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}
