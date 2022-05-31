package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class registration extends AppCompatActivity {

    private TextInputLayout name,lastName,phone,email,password,cpass,idNumber,pin,cPin,dob,confirmationCode;
    private Button register;
    private CheckBox check;
    private CheckedTextView checkView;
    ProgressBar progressBar;


    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpass = findViewById(R.id.cPass);
        idNumber = findViewById(R.id.idNumber);
        pin = findViewById(R.id.pin);
        cPin = findViewById(R.id.cPin);
        dob = findViewById(R.id.dateOfBirth);

        check = findViewById(R.id.checkBox);
        checkView = findViewById(R.id.checked);

        register = findViewById(R.id.register);
        confirmationCode = findViewById(R.id.confirmationCode);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oPenRegister();
            }
        });

    }

    private void oPenRegister() {


        Toast.makeText(registration.this, "Loading ....", Toast.LENGTH_LONG).show();

        final String name1 = name.getEditText().getText().toString().trim();
        final String lastName1 = lastName.getEditText().getText().toString().trim();
        final String phone1 = phone.getEditText().getText().toString().trim();
        final String email1 = email.getEditText().getText().toString().trim();
        final String password1 = password.getEditText().getText().toString().trim();

        final String idNumber1 = idNumber.getEditText().getText().toString().trim();
        final String pin1 = pin.getEditText().getText().toString().trim();

        final String dob1 = dob.getEditText().getText().toString().trim();

        if(confirmationCode() | nameValidation() | lastNameValidation() | mobileValidation() | passwordValidation() | emailValidation()
                | pin() && nameValidation() && lastNameValidation() && mobileValidation() && passwordValidation() && emailValidation()
                && pin() && confirmationCode()){
            if(check.isChecked()) {
                addCustomer(name1,lastName1,phone1,email1,password1,idNumber1,pin1,dob1);
            } else {
                Toast.makeText(registration.this, "Please agree on Terms and Conditions", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(registration.this, "There exists some ERRORS", Toast.LENGTH_LONG).show();

            return;
        }
    }


    private void addCustomer(final String name1, final String lastName1, final String phone1, final String email1, String password1,
                             final String idNumber1, final String pin1, final String dob1) {


        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    customers customer = new customers(name1,lastName1,phone1,email1,idNumber1,pin1,dob1,"Admin");
                    FirebaseDatabase.getInstance().getReference("Customers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CustomerInfo").setValue(customer)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                String uid=mAuth.getUid();

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {

                                        addFireBase(uid);
                                        server(uid);
                                        police(uid);

                                        Toast.makeText(registration.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                                        goBack();
                                    }
                                    else {

                                    }
                                }
                            });
                }else {
                    Toast.makeText(registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private void addFireBase(String uid) {

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("MainBreaker").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("socket1").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("socket2").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("MainLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("SpotsLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("EntranceLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MasterBedRoom").child("DimLightSwitch").child("status").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("MainBreaker").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("lightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("fridgeSocket").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("microwaveSocket").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("socket1").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("socket2").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Kitchen").child("socket3").child("status").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("MainBreaker").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("MainLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("SpotsLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("EntranceLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("DimLightSwitch").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("socket1").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("salon").child("socket2").child("status").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("KidsBedRoom").child("MainBreaker").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("KidsBedRoom").child("light").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("KidsBedRoom").child("socket").child("status").setValue("off");


        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Ac").child("salonAc").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Ac").child("kidsRoomAc").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Ac").child("masterBedRoomAc").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Ac").child("MainBreaker").child("status").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("HotWater").child("status").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MainBreaker").child("status").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("SecuritySensors").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MainGate").setValue("closed");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("ParkingGate").setValue("close");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Out").setValue("no");


        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("KidsBedRoomAc").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("SalonAc").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MasterBedRoomAc").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllSockets").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("AllLights").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoOutSetting").child("MainBreaker").setValue("off");

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoAutoSetting").child("HotWaterHeater").child("on").setValue(0);
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("GoAutoSetting").child("HotWaterHeater").child("off").setValue(0);

        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("Cameras").setValue("off");
        FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("auto").setValue("off");


    }
    private void police(String uid){

        FirebaseDatabase.getInstance().getReference("Police").child(uid);
        FirebaseDatabase.getInstance().getReference("Police").child(uid).child("Counting").setValue(0);
        FirebaseDatabase.getInstance().getReference("Police").child(uid).child("SecuritySensors").setValue("off");

    }

    private void server(String uid) {


        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("socket1").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("socket2").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("MainLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("SpotsLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("EntranceLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("DimLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MasterBedRoom").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("lightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("fridgeSocket").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("microwaveSocket").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("socket1").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("socket2").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("socket3").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Kitchen").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("MainLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("SpotsLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("EntranceLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("DimLightSwitch").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("socket1").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("socket2").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("salon").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("KidsBedRoom").child("light").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("KidsBedRoom").child("socket").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("KidsBedRoom").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("KidsBedRoom").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Ac").child("salonAc").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Ac").child("kidsRoomAc").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Ac").child("masterBedRoomAc").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Ac").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("Ac").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("HotWater").child("TotalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("HotWater").child("TotalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("totalUsage").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("totalAmperage").setValue(0);

        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("MaxAmperage").setValue(5);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("electricity").child("Counting").setValue(1);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("electricity").child("1").child("CurrentConsumption").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("electricity").child("1").child("LastConsumption").setValue(0);
        FirebaseDatabase.getInstance().getReference("Server").child(uid).child("electricity").child("1").child("Date").setValue(0);

    }

    private void goBack() {

        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
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

    private boolean nameValidation(){

        final String name1 = name.getEditText().getText().toString().trim();
        if(name1.isEmpty()){
            name.setError("This Field Can't be Empty");
            return false;
        }else {
            name.setError(null);
            return true;
        }
    }

    private boolean lastNameValidation(){

        final String lastName1 = lastName.getEditText().getText().toString().trim();

        if(lastName1.isEmpty()){
            lastName.setError("This Field Can't be Empty");
            return false;
        }else {
            lastName.setError(null);
            return true;
        }
    }

    private boolean mobileValidation(){

        final String phone1 = phone.getEditText().getText().toString().trim();
        if(phone1.isEmpty()){
            phone.setError("This Field Can't be Empty");
            return false;
        }else {
            phone.setError(null);
            return true;
        }
    }

    private boolean emailValidation(){

        final String email1 = email.getEditText().getText().toString().trim();

        if(email1.isEmpty()){
            email.setError("This Field Can't be Empty");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }

    private boolean passwordValidation(){

        final String password1 = password.getEditText().getText().toString().trim();
        final String cpass1 = cpass.getEditText().getText().toString().trim();

        if(password1.isEmpty()||cpass1.isEmpty()){
            password.setError("This Field Can't be Empty");
            cpass.setError("This Field Can't be Empty");
            return false;
        }

        else if(!password1.equals(cpass1)){
            password.setError("Password Doesn't Match!");
            cpass.setError("Password Doesn't Match!");
            return false;
        }
        else if (!isMatching(password1)){

            password.setError("Password Should Contains Capital,Small Letter and Numbers");
            cpass.setError("Password Doesn't Match!");
            return false;
        }
        else {
            password.setError(null);
            cpass.setError(null);
            return true;
        }
    }

    private boolean pin(){

        final String pin1 = pin.getEditText().getText().toString().trim();
        final String cPin1 = cPin.getEditText().getText().toString().trim();

        if(pin1.isEmpty()||cPin1.isEmpty()){
            pin.setError("This Field Can't be Empty");
            cPin.setError("This Field Can't be Empty");
            return false;
        }

        else if(!pin1.equals(cPin1)){
            pin.setError("Pin Doesn't Match!");
            cPin.setError("Pin Doesn't Match!");
            return false;
        }

        else {
            pin.setError(null);
            cPin.setError(null);
            return true;
        }


    }

    private boolean confirmationCode(){

        final int[] code = {0};
        final int[] va = {0};
        final String cCode = confirmationCode.getEditText().getText().toString().trim();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database1.getReference("Server");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("ConfirmationCode").getValue().toString();
                va[0] = Integer.parseInt(value);
                code[0] = Integer.parseInt(cCode);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        if(va[0] == code[0]){
           return true;
        }
        else {
            return false;
        }
    }

}
