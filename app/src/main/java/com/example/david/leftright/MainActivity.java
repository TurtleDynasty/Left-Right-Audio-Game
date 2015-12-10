package com.example.david.leftright;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isLeft = false;
    int score = 0;
    Context context = this;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AlertDialog.Builder(context)
                .setTitle("Instructions")
                .setMessage("Best played with headphones. Press the side of the screen to indicate which speaker the voice comes from. The voice will try to trick you!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        newCycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean getRandomBoolean(){
        if(Math.random()>0.5) {
            //Log.d("Debug", "true");
            return true;
        } else {
            //Log.d("Debug", "false");
            return false;
        }
    }

    public void setScoreText(int score){
        final TextView textViewToChange = (TextView) findViewById(R.id.textViewScore);
        textViewToChange.setText("Your Score: " + score);
    }

    public void newCycle(){
        isLeft = getRandomBoolean();

        if(isLeft){
            if(getRandomBoolean()){
                mediaPlayer = MediaPlayer.create(context, R.raw.ll);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            } else {
                mediaPlayer = MediaPlayer.create(context, R.raw.rl);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        } else {
            if(getRandomBoolean()){
                mediaPlayer = MediaPlayer.create(context, R.raw.lr);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            } else {
                mediaPlayer = MediaPlayer.create(context, R.raw.rr);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        }
    }

    public void clickLeftButton(View v){
        Log.d("MainActivity", "left");
        if(isLeft){
            score++;
            setScoreText(score);
            newCycle();
        } else {
            score = 0;
            setScoreText(0);
            mediaPlayer = MediaPlayer.create(context, R.raw.incorrect);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
            newCycle();
        }
    }

    public void clickRightButton(View v){
        Log.d("MainActivity", "right");
        if(isLeft){
            score = 0;
            setScoreText(0);
            mediaPlayer = MediaPlayer.create(context, R.raw.incorrect);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
            newCycle();
        } else {
            score++;
            setScoreText(score);
            newCycle();
        }
    }



}
