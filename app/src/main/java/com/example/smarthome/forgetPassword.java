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

public class forgetPassword extends AppCompatActivity {

    EditText em;
    Button send,next;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        em = findViewById(R.id.email);
        send = findViewById(R.id.sendPass);
        next = findViewById(R.id.next);

        firebaseAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ema = em.getText().toString();
                firebaseAuth.sendPasswordResetEmail(ema).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgetPassword.this, "Password sent", Toast.LENGTH_LONG).show();
                            em.setText("");
                        }
                        else {
                            Toast.makeText(forgetPassword.this, "Error!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNext();
            }
        });
    }

    private void openNext() {
        Intent intent = new Intent(this,forgetPasswordNext.class);
        startActivity(intent);
    }
}
