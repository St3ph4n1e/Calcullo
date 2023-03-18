package com.example.calcullo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int result;
    int size;
    TextView resultTextView;
    Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultButton = findViewById(R.id.resultButton);
        resultTextView = findViewById(R.id.resultTextView);
        Intent srcIntent = getIntent();
        result = srcIntent.getIntExtra("resultats",0);
        size = srcIntent.getIntExtra("size",0);
        String score = "Score :\n \n" +result + "/" + size;

        resultTextView.setText(score);
    }
}