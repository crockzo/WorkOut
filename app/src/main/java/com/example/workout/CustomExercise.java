package com.example.workout;

import android.content.Intent;
import android.media.MediaExtractor;
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

public class CustomExercise extends AppCompatActivity {
    //tag message for debug
    private static final String TAG = "my error";

    //textview for exercise name and countdown
    private TextView countDown , setExercise;

    private ImageView setExeImage ;

    int[] arr = new int[10];
    CircularProgressBar circularProgressBar;
    MediaPlayer mediaPlayer;
    String[] exe = new String[10];
    //random image generator
    private String[] imageId = {"one" , "two" , "three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen" ,"fourteen" };
    //HASH MAP OF KEY - > NUMBER VALUE- > IMAGE ID
    public HashMap<String,Integer> ig = new HashMap<String, Integer>();
    Button Home ;
    private int exerciseTimeCounter = 0;
    private int count = 0;
    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_activity);

        exe = getIntent().getExtras().getStringArray("value");
        countDown = (TextView) findViewById(R.id.countDown);
        setExercise  = (TextView) findViewById(R.id.exName);
        setExeImage = (ImageView) findViewById(R.id.exerciseImage);
        Home = (Button) findViewById(R.id.Home);
        mediaPlayer = MediaPlayer.create(this,R.raw.start_workout);
        mediaPlayer.start();
        arrGenerate(arr);
        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);
        setImageId();
        setTimer(i);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomExercise.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //USE TO COUNT DOWN FOR CERTAIN AMMOUNT OF TIME
    @SuppressWarnings("ConstantConditions")
    public void setTimer(int i ){
        if(i < getIntent().getExtras().getStringArray("value").length){
            ++exerciseTimeCounter;
            if(exerciseTimeCounter%3==0){
                setExercise.setText("REST TIME");
                setExeImage.setImageResource(R.drawable.rest_break);
                MyTimer m = new MyTimer(41000,1000);
                m.start();
            }else{
                MyTimer m = new MyTimer(41000,1000,exe[i] , imageId[arr[i]]);
                m.start();
            }
        }


    }

    public class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval ){
            super(millisInFuture , countDownInterval);

        }

        public MyTimer(long millisInFuture, long countDownInterval ,String m , String id){
            super(millisInFuture,countDownInterval);
            setExercise.setText(m);
            //noinspection ConstantConditions
            setExeImage.setImageResource(ig.get(id));

        }
        @Override
        public void onTick(long millisUntilFinished) {
            count = (int)millisUntilFinished/1000;
            countDown.setText(String.valueOf(count));
            circularProgressBar.setProgress((float)(count*2.5));
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public void onFinish() {
            ++i;
            if (i < getIntent().getExtras().getStringArray("value").length){

                setTimer(i);

            }else{
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
        for (int i = 0 ; i <10 ; i++){
            try {
                arr[i] =(int)(Math.random()*10);
            }catch (Exception e){
                Log.e(TAG, "arrGenerate: is not generation stuff" );
            }
        }
        return arr;
    }
}
