package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;






public class goAuto extends AppCompatActivity {

    String uid;
    EditText hour,min,sec,hour2,min2,sec2;
    Button save;
    RadioGroup radioGroup,radioGroup2;
    RadioButton radioButton,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_auto);

        Intent intent = getIntent();
        uid = intent.getStringExtra("message");

        hour = findViewById(R.id.hours);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);

        hour2 = findViewById(R.id.hours2);
        min2 = findViewById(R.id.min2);
        sec2 = findViewById(R.id.sec2);

        save = findViewById(R.id.save1);

        radioGroup = findViewById(R.id.group);
        radioGroup2 = findViewById(R.id.group2);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);


                int radioId2 = radioGroup2.getCheckedRadioButtonId();
                radioButton2 = findViewById(radioId2);

                String r = (String) radioButton.getText();
                String r2 = (String) radioButton2.getText();

                String h = hour.getText().toString();
                String m = min.getText().toString();
                String s = sec.getText().toString();

                String h2 = hour2.getText().toString();
                String m2 = min2.getText().toString();
                String s2 = sec2.getText().toString();

                String on = h+":"+m+":"+s+" "+r;
                String off = h2+":"+m2+":"+s2+" "+r2;

                FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoAutoSetting").child("HotWaterHeater").child("on").setValue(on);
                FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoAutoSetting").child("HotWaterHeater").child("off").setValue(off);


                hour.setText("");
                min.setText("");
                sec.setText("");

                hour2.setText("");
                min2.setText("");
                sec2.setText("");

                Toast.makeText(goAuto.this, "Data Saved!", Toast.LENGTH_LONG).show();

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Customers").child(uid).child("Kitchen").child("microwaveSocket");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
