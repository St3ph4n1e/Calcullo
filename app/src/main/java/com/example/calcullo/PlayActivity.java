package com.example.calcullo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    TextView questionTextView;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;
    CalculList mCalculList;
    Calcul mCalcul;
    int goodAnswers = 0;


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

        Button[] answersButtons = {answerButton1, answerButton2, answerButton3, answerButton4};


        // Récupération du calcul depuis StartActivity :
        Intent srcIntent = getIntent();
        mCalculList = srcIntent.getParcelableExtra("Calculs");
        mCalcul = mCalculList.getNextCalcul();

        setAnswerButtonsOnClickListener();
        playCalcul();
    }

    private void setAnswerButtonsOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String clickedAnswer = clickedButton.getText().toString();
                String correctAnswer = mCalcul.result;
                Button[] answersButtons = {answerButton1, answerButton2, answerButton3, answerButton4};


                if (clickedAnswer.equals(correctAnswer)) {
                    goodAnswers ++;
                    // Si la réponse est correcte, changez la couleur du bouton en vert
                    clickedButton.setBackgroundColor(Color.GREEN);
                    // Affichez un message de bonne réponse
                    Toast.makeText(PlayActivity.this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
                } else {
                    // Si la réponse est incorrecte, changez la couleur du bouton en rouge et trouvez le bouton avec la réponse correcte et changez sa couleur en vert
                    clickedButton.setBackgroundColor(Color.RED);
                    for (Button button : answersButtons) {
                        if (button.getText().toString().equals(correctAnswer)) {
                            button.setBackgroundColor(Color.GREEN); // décommenter cette ligne
                            break;
                        }
                    }
                    // Affichez un message de mauvaise réponse
                    Toast.makeText(PlayActivity.this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
                }

                // Obtenez la prochaine question
                mCalcul = mCalculList.getNextCalcul();
                if (mCalcul == null ) {
                    // Il n'y a plus de questions, affichez les résultats
                     showResults();
                } else {
                    // Jouez la prochaine question


                    playCalcul();
                }
            }
        };
        answerButton1.setOnClickListener(listener);
        answerButton2.setOnClickListener(listener);
        answerButton3.setOnClickListener(listener);
        answerButton4.setOnClickListener(listener);
    }

    private void showResults() {

        int size = mCalculList.size()-1;

        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra("resultats", goodAnswers);
        intent.putExtra("size",size);
        startActivity(intent);
    }

    private void playCalcul() {

        questionTextView.setText(mCalcul.getQuestion());
        Button[] answersButtons = {answerButton1, answerButton2, answerButton3, answerButton4};

        int i = 0;
        for (Button button : answersButtons) {

            // définir le texte de chaque bouton en utilisant la méthode setText() :
            int answer = mCalcul.answers.get(i);
            button.setText(String.valueOf(answer));
            i++;
        }



    }
}






