package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class report extends AppCompatActivity {

    String uid;
    EditText description,brand;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        radioGroup = findViewById(R.id.group2);
        description=findViewById(R.id.description);
        brand = findViewById(R.id.brand);
        send = findViewById(R.id.sendReport);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String des = description.getText().toString();
                String br = brand.getText().toString();
                String r = (String) radioButton.getText();

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                FirebaseDatabase.getInstance().getReference("HomeAppliances").child(uid).child(currentDateTimeString)
                .child(r).child("Brand").setValue(br);
                FirebaseDatabase.getInstance().getReference("HomeAppliances").child(uid).child(currentDateTimeString)
                        .child(r).child("Description").setValue(des);
                description.setText("");
                brand.setText("");
                Toast.makeText(report.this, "Report Submitted", Toast.LENGTH_LONG).show();
                openMain();
            }
        });
    }

    private void openMain() {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {

            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
        }
}
