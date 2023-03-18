package com.example.calcullo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int result;
    int size;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        Intent srcIntent = getIntent();
        result = srcIntent.getIntExtra("resultats",0);
        size = srcIntent.getIntExtra("size",0);

        resultTextView.setText( "Score :\n \n" +result + "/" + size);
    }
}