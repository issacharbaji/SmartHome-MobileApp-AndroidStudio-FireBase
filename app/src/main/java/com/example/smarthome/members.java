package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class members extends AppCompatActivity {

    Button addMembers,members;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        addMembers = findViewById(R.id.addMember);
        members = findViewById(R.id.members);

        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAddMembers();


            }
        });

    }

    private void openAddMembers() {

        Intent intent = new Intent(members.this,addSubAccounts.class);
        intent.putExtra("message", message);
        startActivity(intent);

    }
}
