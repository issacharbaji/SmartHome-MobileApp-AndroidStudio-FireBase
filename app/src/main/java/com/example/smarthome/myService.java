package com.example.smarthome;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.FirebaseDatabase;


public class myService extends Service{


    final class MyThreadClass implements Runnable {

        int serviceID;

        public MyThreadClass(int serviceID) {
            this.serviceID = serviceID;
        }

        @Override
        public void run() {

            int i = 0;


            synchronized (this) {
                while (i < 10) {
                    try {
                        wait(1000);
                        FirebaseDatabase.getInstance().getReference("test").setValue(i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            stopSelf(serviceID);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(myService.this, "Service Starts", Toast.LENGTH_LONG).show();
        Thread t = new Thread(new MyThreadClass(startId));
        t.start();

        return START_STICKY;

    }


    @Override
    public void onDestroy() {

        Toast.makeText(myService.this, "Service Ends", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

