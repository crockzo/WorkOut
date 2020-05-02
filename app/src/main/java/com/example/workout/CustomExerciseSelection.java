package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomExerciseSelection extends AppCompatActivity {

    //array of radioButtons to point to different radio buttons
    private RadioButton[] radioButtons = new RadioButton[10];

    //list to store the selected exercise
    List<String> exerciseList = new ArrayList<String>();

    //to start the workout session
    Button startExercise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_selection);

        //assigening the radioButton to the array elements
        radioButtons[0] = (RadioButton) findViewById(R.id.Barbell_Bench_Press);
        radioButtons[1] = (RadioButton) findViewById(R.id.Flat_Bench_Dumbbell);
        radioButtons[2] = (RadioButton) findViewById(R.id.Low_Incline_Barbell_Bench);
        radioButtons[3] = (RadioButton) findViewById(R.id.One_Arm_High_Cable_Curl);
        radioButtons[4] = (RadioButton) findViewById(R.id.Standing_Cable_Curl);
        radioButtons[5] = (RadioButton) findViewById(R.id.Smith_Machine_Drag);
        radioButtons[6] = (RadioButton) findViewById(R.id.Barbell_Overhead_Shoulder);
        radioButtons[7] = (RadioButton) findViewById(R.id.Reverse_Pec_Deck_Fly);
        radioButtons[8] = (RadioButton) findViewById(R.id.Push_Press);
        radioButtons[9] = (RadioButton) findViewById(R.id.Barbell_Deadlift);
        startExercise = (Button) findViewById(R.id.exerciseStart);

        startExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will geather the selected exercise
                check();

                //string arrray to pass as putextra
                String[] exe = new String[exerciseList.size()];

                for(int i  = 0 ; i< exerciseList.size();i++){
                    exe[i] = exerciseList.get(i);
                }

                //start the custom exercise activity
                Intent intent = new Intent(CustomExerciseSelection.this , CustomExercise.class);
                //sent the selected exercise to the customExercise activity
                intent.putExtra("value" , exe);
                startActivity(intent);
            }
        });




    }

    //checking the radiobutton are selected or not
    private void check(){
        int i = 0;

        while(i<10){
            if (radioButtons[i].isChecked()){
                exerciseList.add(radioButtons[i].getText().toString());
            }
            ++i;
        }
    }




}
