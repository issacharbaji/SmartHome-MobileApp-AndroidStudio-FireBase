package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.regex.Pattern;

public class resetPass2 extends AppCompatActivity {

    String message,value;
    EditText oldPass1,newPass1,newPassCon1;
    Button pass;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass2);


        Intent intent = getIntent();
        message = intent.getStringExtra("message");


        oldPass1 = findViewById(R.id.o);
        newPass1 = findViewById(R.id.n);
        newPassCon1 = findViewById(R.id.c);

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

        auth = FirebaseAuth.getInstance();
        pass = findViewById(R.id.save1);


        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String newP = newPass1.getText().toString();
                final String passCon = newPassCon1.getText().toString();

                if( newP.equals("") || passCon.equals("")){
                    Toast.makeText(resetPass2.this, "Some Fields are Empty!", Toast.LENGTH_LONG).show();

                }
                else {
                        if(isMatching(newP)) {
                            if (newP.equals(passCon)) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (user != null) {
                                    user.updatePassword(newP).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(resetPass2.this, "Password Changed!", Toast.LENGTH_LONG).show();
                                                newPass1.setText("");
                                                newPassCon1.setText("");
                                                openHome();
                                            } else {
                                                Toast.makeText(resetPass2.this, "There are some ERRORS!", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(resetPass2.this, "Confirmation Password Didn't Match!", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(resetPass2.this, "The password must contains capital and small letters and numbers", Toast.LENGTH_LONG).show();
                        }

                }


            }
        });

    }

    private void openHome() {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }
    private static Boolean isMatching(String password){
        boolean inputMatches=true;
        Pattern[] input = new Pattern[3];
        input[0]=Pattern.compile(".*[A-Z].*");
        input[1]=Pattern.compile(".*[a-z].*");
        input[2]=Pattern.compile(".*\\d.*");

        for(Pattern inputR : input){
            if(!inputR.matcher(password).matches()){
                inputMatches=false;
            }

        }
        return inputMatches;

    }


}
