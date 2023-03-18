package com.example.calcullo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    TextView questionTextView;

    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;

    CalculList mCalculList;
    Calcul mCalcul;





    enum CalculState {
        NOT_ANSWERED,
        NEXT_QUESTION,
        RESULTS,
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionTextView = findViewById(R.id.questionTextView);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        answerButton4 = findViewById(R.id.answerButton4);


        // Récupération du calcul depuis StartActivity :
        Intent srcIntent = getIntent();
        mCalculList = srcIntent.getParcelableExtra("Calculs");
        mCalcul = mCalculList.getNextCalcul();

        playCalcul();
    }

    private void playCalcul (){

        questionTextView.setText(mCalcul.getQuestion());
        Button [] answersButtons = {answerButton1,answerButton2,answerButton3,answerButton4};


        int i = 0;
        for (Button button : answersButtons) {
            // définir le texte de chaque bouton en utilisant la méthode setText() :
            int answer = mCalcul.answers.get(i);
            button.setText(String.valueOf(answer));
            i++;
        }

    }



}