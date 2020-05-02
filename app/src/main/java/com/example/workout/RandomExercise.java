package com.example.workout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.random;

public class RandomExercise extends AppCompatActivity {

    //take count of the rest break
    int exerciseTimeCounter = 0;

    //used for the music play
    MediaPlayer mediaPlayer;

    //countdown bar to track the left time
    CircularProgressBar circularProgressBar;

    //take us to the home page of the app which is main_activity
    Button Home ;
    //loge tag
    private static final String TAG = "i am here";

    //textview for exercise name and countdown
    private TextView countDown , setExercise;

    //use to loop throught selectedExe
    private int i = 0;

   //type of exercise key
    private String[] exerciseName = {"chest 1" ,"chest 2" ,"chest 3" ,
            "biceps 1" ,"biceps 2" , "biceps 3" ,
            "shoulder 1" ,"shoulder 2" ,"shoulder 3" ,"shoulder 4" };

    //selection of exercise at a random level
    private String[] selectedExe = new String[5];


    //data set
    private HashMap<String,String> hm=new HashMap<String, String>();

    //random generator
    private int[] arr = new int[5];


    //image view
    private ImageView setExeImage ;

    //random image generator
    private String[] imageId = {"one" , "two" , "three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen" ,"fourteen" };

    //WILL STORE RANDOM IMAGE IDS
    private int[] selectedImage = new int[5];

    //HASH MAP OF KEY - > NUMBER VALUE- > IMAGE ID
    public HashMap<String,Integer> ig = new HashMap<String, Integer>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_activity);

        //assigenment of views to the respective type of Views

        //text countdown of the exercise time
        countDown = (TextView) findViewById(R.id.countDown);

        //to set the name of the exercise
        setExercise  = (TextView) findViewById(R.id.exName);

        //to set the image of the random exercise
        setExeImage = (ImageView) findViewById(R.id.exerciseImage);

        //this will take is to the home page of the app
        Home = (Button) findViewById(R.id.Home);

        //assigining the audio to the mediaPlayer
        mediaPlayer = MediaPlayer.create(this,R.raw.start_workout);
        //this will start the music assigened to the mediaplayer
        mediaPlayer.start();

        //assigining the circular bar the the variable of circular bar
        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);


        //this is to auto generate the index of the exerrcise between 0 to 9
        arr = arrGenerate(arr);

        //set the hashValue key -- pair value of the exercise
        setHashMapValue();

        //this will create the hashmap value accourding to the image name and the image resource id
        setImageId();

        //this willl retrive the crrosponding values to the auto generated array
        generate(arr);

        //this will start the activity process which is set the exercise name and image with th time timer and a progress bar
        setTimer(i);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RandomExercise.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }


    //SETTING HASH MAP VALUE FOR EXERCISE IN KEY VALUE PAIR
    private void setHashMapValue(){
        hm.put(exerciseName[0], "Barbell Bench Press");
        hm.put(exerciseName[1], "Flat Bench Dumbbell Press");
        hm.put(exerciseName[2] , "Low-Incline Barbell Bench Press");
        hm.put(exerciseName[3] ,"One-Arm High-Cable Curl");
        hm.put(exerciseName[4] ,"Standing Cable Curl.");
        hm.put(exerciseName[5] ,"Smith-Machine Drag Curl.");
        hm.put(exerciseName[6] , "Barbell Overhead Shoulder Press");
        hm.put(exerciseName[7] , "Reverse Pec Deck Fly");
        hm.put(exerciseName[8] , "Push Press");
        hm.put(exerciseName[9], "Barbell Deadlif");

    }

    //CREATING HASH MAP OF IMAGE ID WITH KEY VALUE PAIR
    private void setImageId(){
        ig.put(imageId[0] , R.drawable.one);
        ig.put(imageId[1] , R.drawable.two);
        ig.put(imageId[2] , R.drawable.three);
        ig.put(imageId[3] , R.drawable.four);
        ig.put(imageId[4] , R.drawable.five);
        ig.put(imageId[5] , R.drawable.six);
        ig.put(imageId[6] , R.drawable.seven);
        ig.put(imageId[7] , R.drawable.eight);
        ig.put(imageId[8] , R.drawable.nine);
        ig.put(imageId[9] , R.drawable.ten);
        ig.put(imageId[10] , R.drawable.eleven);
        ig.put(imageId[11] , R.drawable.twelve);
        ig.put(imageId[12] , R.drawable.thirteen);
        ig.put(imageId[13] , R.drawable.fourteen);
    }

    //GENERATING RANDOM ARRAY INDEX
    private int[] arrGenerate(int[] arr){
        for (int i = 0 ; i <5 ; i++){
            try {
                arr[i] =(int)(Math.random()*10);
            }catch (Exception e){
                Log.e(TAG, "arrGenerate: is not generation stuff" );
            }
        }
        return arr;
    }

    //GENERATING RANDOM EXERCISE AND IMAGE ID
    private void generate(int[] arr){
        for (int i = 0 ; i <5 ; i++){
            selectedExe[i] = hm.get(exerciseName[arr[i]]);
            try {
                //noinspection ConstantConditions
                selectedImage[i] = ig.get("one");
            }catch (NullPointerException n){
                Log.e(TAG, "generate: null found");
            }
        }
    }


    //USE TO COUNT DOWN FOR CERTAIN AMMOUNT OF TIME
    public void setTimer(int i){
        ++exerciseTimeCounter;
        if(exerciseTimeCounter==4){
            setExercise.setText("REST TIME");
            setExeImage.setImageResource(R.drawable.rest_break);
            MyCountDownTimer m = new MyCountDownTimer(41000,1000);
            m.start();

        }else{

            MyCountDownTimer m = new MyCountDownTimer(41000,1000,selectedExe[i] , imageId[arr[i]]);
            m.start();
        }

    }

    //explain trivial proof and proof by contraduction with example
    public  class MyCountDownTimer extends CountDownTimer {
        int count = 0;
        String exe;
        public MyCountDownTimer(long millisInFuture, long countDownInterval){
            super(millisInFuture,countDownInterval);
        }
        public MyCountDownTimer(long millisInFuture, long countDownInterval ,String s,String imageRes) {
            super(millisInFuture, countDownInterval);
            exe = s;
            setExercise.setText(exe);
            //noinspection ConstantConditions
            setExeImage.setImageResource(ig.get(imageRes));
             }

        @Override
        public void onTick(long millisUntilFinished) {
                count = (int)millisUntilFinished/1000;
                countDown.setText(String.valueOf(count));
                circularProgressBar.setProgress((float)(count*2.5));
               // count++;
        }

        @Override
        public void onFinish() {

            //i will go till  i == lengthOfExercised -1
            ++i;

            //this ensure the break time
            if(exerciseTimeCounter==4){
                setTimer(i);
            }
           else if (i < 5){

               setTimer(i);

           }else{
               //setting the values to the views when the exercise session is over
               setExercise.setText("CONGRATULATION");
               setExeImage.setImageResource(R.drawable.locworkoutlogotransparent);
               countDown.setText("YOU ARE GETTING STRONGER!!");
               countDown.setTextSize(22);
               circularProgressBar.setVisibility(View.INVISIBLE);
               mediaPlayer.stop();
               Home.setVisibility(View.VISIBLE);

           }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}

