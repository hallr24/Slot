package com.example.slot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private TextView nameView;
    private TextView scoreView;
    private TextView rateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        nameView = findViewById(R.id.nameView);
        scoreView = findViewById(R.id.scoreView);
        rateView = findViewById(R.id.rateView);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        String[] entry = dbm.get(name);
        nameView.setText(entry[0]);
        scoreView.setText(entry[1]);
        rateView.setText(entry[2]);

    }

    public void editPressed(View v) {
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("ADD", false);
        i.putExtra("NAME", nameView.getText().toString());
        i.putExtra("SCORE", scoreView.getText().toString());
        i.putExtra("RATE", rateView.getText().toString());
        startActivity(i);
    }


}
