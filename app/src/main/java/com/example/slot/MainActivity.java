package com.example.slot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Drawable strawberry1;
    public Drawable pear1;
    public Drawable cherry1;
    public Drawable grape1;
    public SeekBar Bar;
    public TextView Points;
    public Button Start;
    public Button Rules;
    public ImageView SlotA;
    public ImageView SlotB;
    public ImageView SlotC;
    public Handler handler;
    public Integer speed;
    public gameStart gameStart;
    public slotAChange slotAChange;
    public slotBChange slotBChange;
    public slotCChange slotCChange;
    public Integer picA;
    public Integer picB;
    public Integer picC;
    public boolean on;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strawberry1 = getDrawable(R.drawable.strawberry);
        pear1 = getDrawable(R.drawable.pear);
        cherry1 = getDrawable(R.drawable.cherry);
        grape1 = getDrawable(R.drawable.grape);

        SlotA = findViewById(R.id.SlotA);
        SlotB = findViewById(R.id.SlotB);
        SlotC = findViewById(R.id.SlotC);
        Points = findViewById(R.id.Points);
        speed = 250;

        Bar = findViewById(R.id.Bar);
        Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    speed = 250;
                } else {
                    speed = (250 / progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Start = findViewById(R.id.Start);
        Rules = findViewById(R.id.Rules);
        handler = new Handler();
        on = false;
        gameStart = new gameStart();
        slotAChange = new slotAChange();
        slotBChange = new slotBChange();
        slotCChange = new slotCChange();
        picA = 1;
        picB = 1;
        picC = 1;


    }

    public void startPressed(View v) {
        if(on == false) {
            on = true;
            handler.postDelayed(gameStart, 100);
            handler.postDelayed(slotAChange, speed);
            handler.postDelayed(slotBChange, (speed/2));
            handler.postDelayed(slotCChange, (speed/4));
        } else {
            on = false;
            handler.removeCallbacks(gameStart);
            handler.removeCallbacks(slotAChange);
            handler.removeCallbacks(slotBChange);
            handler.removeCallbacks(slotCChange);
            points();
        }
    }


    public class gameStart implements Runnable {

        @Override
        public void run() {
            handler.postDelayed(gameStart,100);

        }
    }

    public void rulesPressed(View v) {
        Intent i = new Intent(this, RulesActivity.class);
        startActivity(i);
    }

    public void points(){
        if(picA == picB && picB == picC) {
            Points.setText("Points 100");
        } else if(picA == picB | picA == picC | picB == picC) {
            Points.setText("Points 50");
        } else {
            Points.setText("Points 10");
        }
    }

    public class slotAChange implements Runnable {
        @Override
        public void run() {
            if(picA == 1){
                SlotA.setImageDrawable(cherry1);
            } else if(picA == 2){
                SlotA.setImageDrawable(grape1);
            } else if(picA == 3){
                SlotA.setImageDrawable(pear1);
            } else {
                SlotA.setImageDrawable(strawberry1);
            }
            if(picA < 4) {
                picA++;
            } else {
                picA = 1;
            }
            handler.postDelayed(slotAChange, speed);
        }
    }

    public class slotBChange implements Runnable {
        @Override
        public void run() {
            if(picB == 1){
                SlotB.setImageDrawable(cherry1);
            } else if(picB == 2){
                SlotB.setImageDrawable(grape1);
            } else if(picB == 3){
                SlotB.setImageDrawable(pear1);
            } else {
                SlotB.setImageDrawable(strawberry1);
            }
            if(picB < 4) {
                picB++;
            } else {
                picB = 1;
            }
            handler.postDelayed(slotBChange, (speed/2));
        }
    }

    public class slotCChange implements Runnable {
        @Override
        public void run() {
            if(picC == 1){
                SlotC.setImageDrawable(cherry1);
            } else if(picC == 2){
                SlotC.setImageDrawable(grape1);
            } else if(picC == 3){
                SlotC.setImageDrawable(pear1);
            } else {
                SlotC.setImageDrawable(strawberry1);
            }
            if(picC < 4) {
                picC++;
            } else {
                picC = 1;
            }
            handler.postDelayed(slotCChange, (speed/4));
        }
    }



}
