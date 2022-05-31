package com.example.smarthome;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

public class test extends AppCompatActivity {


    private final static String SAMPLE_INPUT = "0.932833333 0.003483333 0.932833333 0.0000333 0.12415 0.013083333 0.001516667 36.14 18000 0";

    private Handler handler;
    private TFLiteModel tfLiteModel;
    Button button;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(() -> tfLiteModel.load());
    }

    @Override
    protected void onStop() {
        handler.post(() -> tfLiteModel.unload());
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button = findViewById(R.id.button);
        handler = new Handler();
        tfLiteModel = new TFLiteModel(getApplicationContext());

        Button buttonPredict = findViewById(R.id.button);
        buttonPredict.setOnClickListener(e -> {
            predict(SAMPLE_INPUT);
        });
    }

    private void predict(String input) {
        handler.post(() -> {
            String result = tfLiteModel.classify(SAMPLE_INPUT);
            showResult(result);
        });
    }

    private void showResult(String result) {
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), "Result: " + result, Toast.LENGTH_LONG).show();
        });
    }
}
