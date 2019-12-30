package com.example.heremaps;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firebase_Helper2 {

    FirebaseDatabase database;
    DatabaseReference myRef,dbRef;

    Firebase_Helper2() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Log.d("Firebase_Helper2","FireBase Object Created");
    }
        public void insert(String zone,String sub_zone,int i) {
            myRef.child("Zone").child(zone).child("Subzone").child(sub_zone).child("User"+i+":").setValue(i);

        }

//        ====Deleting user when he/she changes the zone=======

        public void delete(String zone,String sub_zone,int i){
            if(i>10){
              myRef.child("Zone").child(zone).child("Subzone").child(sub_zone).child("User"+(i-10)+":").removeValue();
            }
        }

        //===========Retrieveing users on alert raise============

        public void retrieve(String zone,String sub_zone){
            dbRef=database.getReference();

            dbRef.addValueEventListener(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                            Map<String, String> td = (HashMap<String,String>) dataSnapshot.getValue();
                            List<String> values = new ArrayList<String> (td.values());
//                            user = singleSnapshot.getValue();
                            Log.d("Abhishek",values.toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
}




