package com.example.sanestreet.testingfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //UI elements
    TextView mTextViewOpgBesk;
    private static final String TAG = "MainActivity";

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbSpoergsmaal = dbRef.child("spoergsmaal");
    DatabaseReference dbSpoergsmaal_1 = dbSpoergsmaal.child("spoergsmaal_1");
    DatabaseReference OpgBesk_1 = dbSpoergsmaal_1.child("problemFormulering");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get UI elements
        mTextViewOpgBesk  = (TextView)findViewById(R.id.textViewOpgBesk);

    }

    @Override
    protected void onStart(){
        super.onStart();

        OpgBesk_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG,"Opgaven lyder: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled",   databaseError.toException());
            }
        });
    }
}
