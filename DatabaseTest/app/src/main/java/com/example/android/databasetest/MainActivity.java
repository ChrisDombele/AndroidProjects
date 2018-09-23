package com.example.android.databasetest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Error";

    // This references the root of the JSON tree
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mConditionRef;

    private TextView mTitleTextView;
    private Button mFishButton;
    private Button mSteakButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI elements
        mTitleTextView = (TextView) findViewById(R.id.ghost);
        mFishButton = (Button) findViewById(R.id.fish);
        mSteakButton = (Button) findViewById(R.id.steak);



    }


    @Override
    protected void onStart() {
        super.onStart();
        mConditionRef = mDatabase.child("Food");

        // Read from the database
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String text = dataSnapshot.getValue(String.class);
                mTitleTextView.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // On button click change data to fish
        mFishButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mConditionRef.setValue("Fish");

            }

        });

        // On button click change data to steak
        mSteakButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mConditionRef.setValue("Steak");

            }

        });

    }
}
