package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logIn extends AppCompatActivity {

    private Button button;
    FirebaseAuth mAuth;
    EditText user, pass;
    TextView forgetP,forgetU;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    String ref;





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        user = findViewById(R.id.userName);
        pass = findViewById(R.id.pass);
        progressBar = findViewById(R.id.progressBar);

        forgetP = findViewById(R.id.forget);
        forgetU = findViewById(R.id.forgetUser);
        forgetP.setPaintFlags(forgetP.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgetU.setPaintFlags(forgetU.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgetPassword();
            }
        });
        forgetU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgetUser();

            }
        });



        mAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userLogIn();


            }
        });
    }

    private void openForgetUser() {

        Intent intent = new Intent(this,forgetUserName.class);
        startActivity(intent);
    }

    private void userLogIn() {

        final String userName = user.getText().toString();
        final String password = pass.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                final String uid = mAuth.getUid();


                if (task.isSuccessful()) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference("Customers").child(uid).child("CustomerInfo");


                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.child("role").getValue().toString();
                            if(value.equals("Admin")){
                                progressBar.setVisibility(View.GONE);
                                user.setText("");
                                pass.setText("");
                                Intent intent = new Intent(logIn.this, main.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("message", uid);
                                startActivity(intent);
                            }
                            else if(value.equals("child")){


                                ref = dataSnapshot.child("reference").getValue().toString();
                                Toast.makeText(logIn.this, ref, Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                user.setText("");
                                pass.setText("");
                                Intent intent = new Intent(logIn.this, kidsBedRoom.class);
                                intent.putExtra("message", ref);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                            else if (value.equals("wife")){

                            }
                            else if (value.equals("other")){

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void openForgetPassword() {

        Intent intent = new Intent(this,forgetPassword.class);
        startActivity(intent);


    }



}
