package com.example.workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //text view used as button to start the app
    TextView letsWorkout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflating the layout for this activity
        setContentView(R.layout.activity_main);

        //assiging the view to the textView object
        letsWorkout = (TextView) findViewById(R.id.letsworkout);

        //called when the letsWorkout got clicked
        letsWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this will indicate us to get to new activity called ExerciseSelection
                Intent intent = new Intent(MainActivity.this , ExerciseSelection.class);

                //this will start the ExerciseSelection  activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //this will inflate menu to the main activity ,,, a menu --> main_menu in res folder
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //this will give the id of the menu item got selected
       switch(item.getItemId()){
           case R.id.detail:

               //this will indicate us to get to new activity called AboutDeveloper
               Intent intent = new Intent(MainActivity.this , AboutDeveloper.class);

               //start the AboutDeveloper activity
               startActivity(intent);
               return true;
           case R.id.about:

               //this will indicate us to get to new activity called AboutApp
               Intent m = new Intent(MainActivity.this , AboutApp.class);

               //starts aboutApp activity
               startActivity(m);
               return true;
           default:return super.onOptionsItemSelected(item);
       }
    }
}
