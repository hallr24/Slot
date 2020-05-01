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

    public Drawable BDSign;
    public Drawable GDS;
    public Drawable gold;
    public Drawable WDS;
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
    public int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BDSign = getDrawable(R.drawable.bdsign);
        GDS = getDrawable(R.drawable.gds);
        gold = getDrawable(R.drawable.gold);
        WDS = getDrawable(R.drawable.wds);

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

    public void onSaveInstanceState( Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("COUNT", count);
        bundle.putBoolean("ON", on);
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

    public void addPressed(View v) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void viewPressed(View v) {
        Intent i = new Intent(this, TitleViewActivity.class);
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
                SlotA.setImageDrawable(BDSign);
            } else if(picA == 2){
                SlotA.setImageDrawable(WDS);
            } else if(picA == 3){
                SlotA.setImageDrawable(GDS);
            } else {
                SlotA.setImageDrawable(gold);
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
                SlotB.setImageDrawable(BDSign);
            } else if(picB == 2){
                SlotB.setImageDrawable(WDS);
            } else if(picB == 3){
                SlotB.setImageDrawable(GDS);
            } else {
                SlotB.setImageDrawable(gold);
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
                SlotC.setImageDrawable(BDSign);
            } else if(picC == 2){
                SlotC.setImageDrawable(WDS);
            } else if(picC == 3){
                SlotC.setImageDrawable(GDS);
            } else {
                SlotC.setImageDrawable(gold);
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
