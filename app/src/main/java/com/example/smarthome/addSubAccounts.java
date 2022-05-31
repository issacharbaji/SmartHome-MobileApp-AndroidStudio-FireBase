package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class addSubAccounts extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    private EditText name, email, password, cpass , role;
    FirebaseAuth mAuth;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_accounts);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        role = findViewById(R.id.role);

        button = findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String na = name.getText().toString().trim();
                String em = email.getText().toString().trim();
                String pa = password.getText().toString().trim();
                String cpa = cpass.getText().toString().trim();
                String ro = role.getText().toString().trim();



                if(na.equals("")||em.equals("")||pa.equals("")||cpa.equals("")||ro.equals("")){
                    empty();
                }
                else {
                    if(pa.equals(cpa))
                        if (isMatching(cpa)) {
                            addMembers(na, em, pa, cpa, ro);
                        } else {
                            password.setText("");
                            cpass.setText("");
                            notMatching();
                        }
                    else {
                        password.setText("");
                        cpass.setText("");
                        doesntMatch();
                    }

                }

            }
        });


    }

    private void openMain() {

        Intent intent = new Intent(this,members.class);
        startActivity(intent);

    }

    public void notMatching() {
        Toast.makeText(addSubAccounts.this,"There should be Capital and Small letters in the password",Toast.LENGTH_LONG).show();
    }

    public void empty() {

        Toast.makeText(addSubAccounts.this,"Some Fields are Empty",Toast.LENGTH_LONG).show();
    }

    public void doesntMatch() {

        Toast.makeText(addSubAccounts.this, "Password Doesn't Match", Toast.LENGTH_LONG).show();
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
    public boolean addMembers(final String na, final String em, final String pa, final String cpa, final String ro) {

        //progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(em, pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    users user = new users(na, em, ro,message);
                    FirebaseDatabase.getInstance().getReference("Customers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CustomerInfo").setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(addSubAccounts.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                        openMain();

                                    } else {

                                    }
                                }
                            });
                } else {
                    Toast.makeText(addSubAccounts.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return true;
    }
}
