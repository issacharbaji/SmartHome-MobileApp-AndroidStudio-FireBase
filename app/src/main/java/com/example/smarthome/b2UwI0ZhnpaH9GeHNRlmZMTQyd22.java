package com.example.smarthome;

import android.view.Gravity;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class b2UwI0ZhnpaH9GeHNRlmZMTQyd22 {


    DatabaseReference databaseReference;
    String uid="b2UwI0ZhnpaH9GeHNRlmZMTQyd22";
    int MaxAm;
    double hw, li , so, fr,mi,sw,aC;

    public void maxAmperage(){

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child(uid);

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MaxAmperage").getValue().toString();
                MaxAm = Integer.parseInt(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void getAmperageSensors(){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = database3.getReference("Server").child("AmperageSensors");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ac = dataSnapshot.child("ac").getValue().toString();
                String fridge = dataSnapshot.child("fridge").getValue().toString();
                String hotWater = dataSnapshot.child("hotWater").getValue().toString();
                String light = dataSnapshot.child("light").getValue().toString();
                String microwave = dataSnapshot.child("microwave").getValue().toString();
                String socket = dataSnapshot.child("socket").getValue().toString();
                String Switch = dataSnapshot.child("switch").getValue().toString();

                aC = Double.parseDouble(ac);
                fr = Double.parseDouble(fridge);
                hw = Double.parseDouble(hotWater);
                li = Double.parseDouble(light);
                mi = Double.parseDouble(microwave);
                so = Double.parseDouble(socket);
                sw = Double.parseDouble(Switch);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void kidsBedRoomSocket() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("KidsBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("KidsBedRoom")
                .child("socket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("KidsBedRoom").child("socket").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kidsBedRoomLight() {

        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("KidsBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("light").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("KidsBedRoom")
                .child("light");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("KidsBedRoom").child("light").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void totalUsageKidsBedRoom(){

        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("KidsBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket").getValue().toString();
                String value1 = dataSnapshot.child("light").getValue().toString();
                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid).child("KidsBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("KidsBedRoom").child("TotalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void kidsBedRoomAmperage(){

        final double light = 0.54;
        final double socket = 0.86;
        final double total = light+socket;
        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("KidsBedRoom");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("light").child("status").getValue().toString();
                String value1 = dataSnapshot.child("socket").child("status").getValue().toString();
                if(value.equals("on") && value1.equals("off")){
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("KidsBedRoom").child("TotalAmperage").setValue(light);
                }
               else if(value.equals("off")&& value1.equals("on")){
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("KidsBedRoom").child("TotalAmperage").setValue(socket);
                }
               else if(value.equals("on")&&value1.equals("on")){
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("KidsBedRoom").child("TotalAmperage").setValue(total);
                }
                else {
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("KidsBedRoom").child("TotalAmperage").setValue(0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    public void masterBedRoomDimLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("DimLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("DimLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("DimLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void masterBedRoomEntranceLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("EntranceLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("EntranceLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("EntranceLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void masterBedRoomMainLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MainLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("MainLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("MainLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void masterBedRoomSpotLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("SpotsLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("SpotsLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("SpotsLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void masterBedRoomSocket1() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket1").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("socket1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("socket1").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void masterBedRoomSocket2() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket2").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("MasterBedRoom")
                .child("socket2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("MasterBedRoom").child("socket2").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void totalUsageMasterBedRoom(){

        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        final int[] v1 = new int[1];
        final int[] v2 = new int[1];
        final int[] v3 = new int[1];
        final int[] v4 = new int[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child("DimLightSwitch").getValue().toString();
                String value1 = dataSnapshot.child("EntranceLightSwitch").getValue().toString();
                String value2 = dataSnapshot.child("socket1").getValue().toString();
                String value3 = dataSnapshot.child("socket2").getValue().toString();
                String value4 = dataSnapshot.child("MainLightSwitch").getValue().toString();
                String value5 = dataSnapshot.child("SpotsLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
                v1[0] = Integer.parseInt(value2);
                v2[0] = Integer.parseInt(value3);
                v3[0] = Integer.parseInt(value4);
                v4[0] = Integer.parseInt(value5);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid).child("MasterBedRoom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] +  v1[0] + v2[0] +v3[0] + v4[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void masterBedRoomAmperage() {

        final double[] value = {0};

        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("EntranceLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("MainLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        databaseReference = database3.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("SpotsLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        databaseReference = database4.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("DimLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        databaseReference = database5.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("socket1");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database6 = FirebaseDatabase.getInstance();
        databaseReference = database6.getReference("Customers").child(uid)
                .child("MasterBedRoom").child("socket2");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("MasterBedRoom").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }








    public void salonDimLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("DimLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("DimLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("DimLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonEntranceLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("EntranceLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("EntranceLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("EntranceLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonMainLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("MainLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("MainLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("MainLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void salonSpotLight() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("SpotsLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("SpotsLightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("SpotsLightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonSocket1() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket1").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("socket1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("socket1").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonSocket2() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket2").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("salon")
                .child("socket2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("salon").child("socket2").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void totalUsageSalon(){

        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        final int[] v1 = new int[1];
        final int[] v2 = new int[1];
        final int[] v3 = new int[1];
        final int[] v4 = new int[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child("DimLightSwitch").getValue().toString();
                String value1 = dataSnapshot.child("EntranceLightSwitch").getValue().toString();
                String value2 = dataSnapshot.child("socket1").getValue().toString();
                String value3 = dataSnapshot.child("socket2").getValue().toString();
                String value4 = dataSnapshot.child("MainLightSwitch").getValue().toString();
                String value5 = dataSnapshot.child("SpotsLightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
                v1[0] = Integer.parseInt(value2);
                v2[0] = Integer.parseInt(value3);
                v3[0] = Integer.parseInt(value4);
                v4[0] = Integer.parseInt(value5);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid).child("salon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] +  v1[0] + v2[0] +v3[0] + v4[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonAmperage() {

        final double[] value = {0};

        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("salon").child("EntranceLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Customers").child(uid)
                .child("salon").child("MainLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        databaseReference = database3.getReference("Customers").child(uid)
                .child("salon").child("SpotsLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        databaseReference = database4.getReference("Customers").child(uid)
                .child("salon").child("DimLightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        databaseReference = database5.getReference("Customers").child(uid)
                .child("salon").child("socket1");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database6 = FirebaseDatabase.getInstance();
        databaseReference = database6.getReference("Customers").child(uid)
                .child("salon").child("socket2");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {
                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];
                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("salon").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


















    public void kitchenFridgeSocket() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("fridgeSocket").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("fridgeSocket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(300);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("fridgeSocket").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenMicrowaveSocket() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("microwaveSocket").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("microwaveSocket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(300);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("microwaveSocket").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenLightSwitch() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("lightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("lightSwitch");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("lightSwitch").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenSocket1() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket1").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("socket1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("socket1").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenSocket2() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket2").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("socket2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("socket2").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenSocket3() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("socket3").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Kitchen")
                .child("socket3");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(1000);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Kitchen").child("socket3").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void totalUsageKitchen(){

        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        final int[] v1 = new int[1];
        final int[] v2 = new int[1];
        final int[] v3 = new int[1];
        final int[] v4 = new int[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child("fridgeSocket").getValue().toString();
                String value1 = dataSnapshot.child("microwaveSocket").getValue().toString();
                String value2 = dataSnapshot.child("socket1").getValue().toString();
                String value3 = dataSnapshot.child("socket2").getValue().toString();
                String value4 = dataSnapshot.child("socket3").getValue().toString();
                String value5 = dataSnapshot.child("lightSwitch").getValue().toString();
                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
                v1[0] = Integer.parseInt(value2);
                v2[0] = Integer.parseInt(value3);
                v3[0] = Integer.parseInt(value4);
                v4[0] = Integer.parseInt(value5);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid).child("Kitchen");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] +  v1[0] + v2[0] +v3[0] + v4[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kitchenAmperage() {

        final double[] value = {0};

        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("Kitchen").child("fridgeSocket");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += fr;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= fr;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Customers").child(uid)
                .child("Kitchen").child("lightSwitch");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += li;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= li;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        databaseReference = database3.getReference("Customers").child(uid)
                .child("Kitchen").child("microwaveSocket");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += mi;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= mi;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        databaseReference = database4.getReference("Customers").child(uid)
                .child("Kitchen").child("socket1");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        databaseReference = database5.getReference("Customers").child(uid)
                .child("Kitchen").child("socket2");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database6 = FirebaseDatabase.getInstance();
        databaseReference = database6.getReference("Customers").child(uid)
                .child("Kitchen").child("socket3");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += so;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {
                    va1[0] -= so;
                    flag1[0]--;
                    value[0]+=va1[0];
                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Kitchen").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void hotWater() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("HotWater");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("TotalUsage").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("HotWater");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(300);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("HotWater").child("TotalUsage").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void hotWaterTotalAmperage(){




        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("HotWater");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("status").getValue().toString();

                if(value.equals("on")){
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("HotWater").child("TotalAmperage").setValue(hw);
                }

                if(value.equals("off")){
                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                            .child("HotWater").child("TotalAmperage").setValue(0);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void kidsBedRoomAc() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Ac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("kidsRoomAc").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Ac")
                .child("kidsRoomAc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(200);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Ac").child("kidsRoomAc").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void masterBedRoomAc() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Ac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("masterBedRoomAc").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Ac")
                .child("masterBedRoomAc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(200);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Ac").child("masterBedRoomAc").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salonAc() {


        final int[] u = new int[1];
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Ac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("salonAc").getValue().toString();
                u[0] = Integer.parseInt(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Customers").child(uid).child("Ac")
                .child("salonAc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            Thread t = null;
            int flag=0;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                String value = dataSnapshot.child("status").getValue().toString();
                if (value.equals("on")) {
                    flag++;
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                while (!isInterrupted()) {
                                    Thread.sleep(200);
                                    u[0]++;
                                    FirebaseDatabase.getInstance().getReference("Server").child(uid)
                                            .child("Ac").child("salonAc").setValue(u[0]);
                                }
                            } catch (InterruptedException e) {
                            }
                        }
                    };
                    t.start();
                }
                if(value.equals("off") && flag == 1 ) {
                    t.interrupt();
                    flag--;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void totalUsageAc(){


        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        final int[] v1 = new int[1];


        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid).child("Ac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child("kidsRoomAc").getValue().toString();
                String value1 = dataSnapshot.child("masterBedRoomAc").getValue().toString();
                String value2 = dataSnapshot.child("salonAc").getValue().toString();

                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
                v1[0] = Integer.parseInt(value2);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid).child("Ac");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] +  v1[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Ac").child("TotalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void totalAmperageAc(){


        final double[] value = {0};


        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Customers").child(uid)
                .child("Ac").child("kidsRoomAc");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += aC;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= aC;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Ac").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Customers").child(uid)
                .child("Ac").child("masterBedRoomAc");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += aC;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= aC;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Ac").child("TotalAmperage").setValue(value[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        databaseReference = database3.getReference("Customers").child(uid)
                .child("Ac").child("salonAc");

        databaseReference.addValueEventListener(new ValueEventListener() {

            final int[] flag1 = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final double[] va1 = {0};
                String value1 = dataSnapshot.child("status").getValue().toString();
                if (value1.equals("on")) {
                    flag1[0]++;
                    va1[0]=0;
                    va1[0] += aC;
                    value[0]+=va1[0];
                }
                if (value1.equals("off") && flag1[0] == 1) {

                    va1[0] -= aC;
                    flag1[0]--;
                    value[0]+=va1[0];

                }
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("Ac").child("TotalAmperage").setValue(value[0]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void totalUsage(){

        final int[] total = new int[1];
        final int[] u = new int[1];
        final int[] v = new int[1];
        final int[] v1 = new int[1];
        final int[] v2 = new int[1];
        final int[] v3 = new int[1];
        final int[] v4 = new int[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String value = dataSnapshot.child("Kitchen").child("TotalUsage").getValue().toString();
                String value1 = dataSnapshot.child("salon").child("TotalUsage").getValue().toString();
                String value2 = dataSnapshot.child("MasterBedRoom").child("TotalUsage").getValue().toString();
                String value3 = dataSnapshot.child("KidsBedRoom").child("TotalUsage").getValue().toString();
                String value4 = dataSnapshot.child("HotWater").child("TotalUsage").getValue().toString();
                String value5 = dataSnapshot.child("Ac").child("TotalUsage").getValue().toString();

                u[0] = Integer.parseInt(value);
                v[0] = Integer.parseInt(value1);
                v1[0] = Integer.parseInt(value2);
                v2[0] = Integer.parseInt(value3);
                v3[0] = Integer.parseInt(value4);
                v4[0] = Integer.parseInt(value5);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] +  v1[0] + v2[0] +v3[0] + v4[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("totalUsage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public void totalAmperage(){

        final double[] total = new double[1];
        final double[] u = new double[1];
        final double[] v = new double[1];
        final double[] v1 = new double[1];
        final double[] v2 = new double[1];
        final double[] v3 = new double[1];
        final double[] v4 = new double[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Kitchen").child("TotalAmperage").getValue().toString();
                String value1 = dataSnapshot.child("salon").child("TotalAmperage").getValue().toString();
                String value2 = dataSnapshot.child("MasterBedRoom").child("TotalAmperage").getValue().toString();
                String value3 = dataSnapshot.child("KidsBedRoom").child("TotalAmperage").getValue().toString();
                String value4 = dataSnapshot.child("HotWater").child("TotalAmperage").getValue().toString();
                String value5 = dataSnapshot.child("Ac").child("TotalAmperage").getValue().toString();

                u[0] = Double.parseDouble(value);
                v[0] = Double.parseDouble(value1);
                v1[0] = Double.parseDouble(value2);
                v2[0] = Double.parseDouble(value3);
                v3[0] = Double.parseDouble(value4);
                v4[0] = Double.parseDouble(value5);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        databaseReference = database2.getReference("Server").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                total[0] = v[0] + u[0] + v1[0] + v2[0] +v3[0] + v4[0];
                FirebaseDatabase.getInstance().getReference("Server").child(uid)
                        .child("totalAmperage").setValue(total[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public void amperageCheck(){

        final double[] value1 = new double[1];

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        databaseReference = database1.getReference("Server").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("totalAmperage").getValue().toString();
                value1[0] = Double.parseDouble(value);
                if(value1[0] > MaxAm){
                    FirebaseDatabase.getInstance().getReference("Customers").child(uid).child("MainBreaker").child("status").setValue("off");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
