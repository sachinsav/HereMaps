package com.example.heremaps;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_Helper2 {

    FirebaseDatabase database;
    DatabaseReference myRef;

    Firebase_Helper2() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Log.d("Firebase_Helper2","FireBase Object Created");
    }
        public void insert(String zone,String subzone,int i) {

            myRef.child("Zone").child(zone).child("Subzone").child(subzone).child("User"+i+":").setValue(i);

        }

        public void delete(String zone,String subzone,int i){
            if(i>10){
              myRef.child("Zone").child(zone).child("Subzone").child(subzone).child("User"+(i-10)+":").removeValue();
        }
}
}



