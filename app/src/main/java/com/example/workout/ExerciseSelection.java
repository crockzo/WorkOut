package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseSelection extends AppCompatActivity {

    //text view to select between random and customize workout routine
    TextView randomB , customizeB ;

    //tag for the logCat
    String TAG = " i am here";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //a layout is inflated to this class
        setContentView(R.layout.exercise_selection);

        //assigining the view to the textView
        randomB = (TextView) findViewById(R.id.random);
        customizeB =(TextView) findViewById(R.id.custom);

        //when randomB got clicked
        randomB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //this will indicate us to get to new activity called RandomExercise
                Intent intent = new Intent(ExerciseSelection.this , RandomExercise.class);

                //start the activity
                startActivity(intent);
            }
        });


        //when customizeB got clicked
        customizeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will indicate us to get to new activity called CustomExerciseSelection
                Intent intent = new Intent(ExerciseSelection.this , CustomExerciseSelection.class);

                //start the activity
                startActivity(intent);
            }
        });




    }
}
