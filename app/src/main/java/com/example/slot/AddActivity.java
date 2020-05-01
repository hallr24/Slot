package com.example.slot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText nameBox;
    private EditText scoreBox;
    private EditText rateBox;
    private Button addButton;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        nameBox = findViewById(R.id.nameBox);
        scoreBox = findViewById(R.id.scoreBox);
        rateBox = findViewById(R.id.rateBox);
        addButton = findViewById(R.id.addButton);
        Intent i = getIntent();
        add = i.getBooleanExtra("ADD",true);
        if (add) {
            addButton.setText("ADD");
        } else {
            addButton.setText("EDIT");
            nameBox.setText(i.getStringExtra("NAME"));
            scoreBox.setText(i.getStringExtra("SCORE"));
            rateBox.setText(i.getStringExtra("RATE"));
        }
    }
    public void addPressed(View v) {
        String name = nameBox.getText().toString();
        String score = scoreBox.getText().toString();
        String rate = rateBox.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);
        if (add)
            dbm.insert(name, score, rate);
        else
            dbm.updateByPlayer(name, score);
        finish();
    }
}
