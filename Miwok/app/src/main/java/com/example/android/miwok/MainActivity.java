package com.example.android.miwok;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //* another way to create onClickListener is to create new java class (NumbersClickListener.Java)
        // and implement the
        //onClickListener interface with the onClick abstract method in the said java class

        //NumbersClickListener clickListener = new NumbersClickListener();

        //Find the view that shows the numbers category
        TextView numbers = (TextView)findViewById(R.id.numbers);

        //when doing * way then the bottom line would be numbers.setOnClickListener(clickListener);
        //so then it can be - setOnClickListener method is called on numbers view then numbers
        //stores the object clickListener and when that view is clicked. something will happen.

        //Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Create a new intent to open the {@link NumbersActivity}
                //this is an explicit intent. intent knows where its going
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                //Start the new activity
                startActivity(numbersIntent);

            }

        });

        TextView family = (TextView)findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Create a new intent to open the {@link FamilyActivity}
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                //Start the new activity
                startActivity(familyIntent);

            }

        });

        TextView colors = (TextView)findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Create a new intent to open the {@link ColorsActivity}
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                //Start the new activity
                startActivity(colorsIntent);

            }

        });

        TextView phrases = (TextView)findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Create a new intent to open the {@link PhrasesActivity}
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                //Start the new activity
                startActivity(phrasesIntent);

            }

        });


    }

}
