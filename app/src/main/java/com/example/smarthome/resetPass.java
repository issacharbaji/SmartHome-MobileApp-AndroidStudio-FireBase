package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class resetPass extends AppCompatActivity {

    String message,value;
    TextInputLayout oldPass,newPass,newPassCon;
    Button pass;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");




        pass = findViewById(R.id.save);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String old = oldPass.getEditText().getText().toString().trim();
                final String newP = newPass.getEditText().getText().toString().trim();
                final String passCon = newPassCon.getEditText().getText().toString().trim();


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("Customers").child(message).child("CustomerInfo");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        value = dataSnapshot.child("email").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                if (newP.equals(passCon)) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    AuthCredential credential = EmailAuthProvider
                            .getCredential(value, old);

                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(newP).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (!task.isSuccessful()) {
                                                    Snackbar snackbar_fail = Snackbar.make(coordinatorLayout, "Something went wrong. Please try" +
                                                            " again later", Snackbar.LENGTH_LONG);
                                                    snackbar_fail.show();
                                                } else {
                                                    Snackbar snackbar_su = Snackbar.make(coordinatorLayout, "Password Successfully Changed", Snackbar.LENGTH_LONG);
                                                    snackbar_su.show();
                                                }

                                            }
                                        });
                                    } else {
                                        Snackbar snackbar_su = Snackbar.make(coordinatorLayout, "Failed", Snackbar.LENGTH_LONG);
                                        snackbar_su.show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(resetPass.this, "Confirmation Password Didn't Match!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
