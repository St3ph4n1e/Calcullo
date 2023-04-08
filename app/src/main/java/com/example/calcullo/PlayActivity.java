package com.example.calcullo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    TextView questionTextView;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;

    ImageButton left;
    ImageButton right;
    CalculList mCalculList;
    Calcul mCalcul;
    int goodAnswers = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionTextView = findViewById(R.id.questionTextView);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        answerButton4 = findViewById(R.id.answerButton4);
        left = findViewById(R.id.LeftImageButton);
        right = findViewById(R.id.RightImageButton);


        Button[] answersButtons = {answerButton1, answerButton2, answerButton3, answerButton4};


        // Récupération du calcul depuis StartActivity :
        Intent srcIntent = getIntent();
        mCalculList = srcIntent.getParcelableExtra("Calculs");
        mCalcul = mCalculList.getNextCalcul();

        setAnswerButtonsOnClickListener();
        playCalcul();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcul previousCalcul = mCalculList.getPreviousCalcul();
                if (previousCalcul != null) {
                    mCalcul = previousCalcul;
                    playCalcul();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcul nextCalcul = mCalculList.getNextCalcul();
                if (nextCalcul != null) {
                    mCalcul = nextCalcul;
                    playCalcul();
                }
            }
        });


    }




    private void setAnswerButtonsOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String clickedAnswer = clickedButton.getText().toString();
                String correctAnswer = mCalcul.result;
                Button[] answersButtons = {answerButton1, answerButton2, answerButton3, answerButton4};
                int couleurButton = ContextCompat.getColor(PlayActivity.this, R.color.blueButton);

                if (clickedAnswer.equals(correctAnswer)) {
                    goodAnswers ++;
                    // Si la réponse est correcte, changer la couleur du bouton en vert
                    clickedButton.setBackgroundColor(Color.GREEN);
                    // Afficher un message de bonne réponse
                    Toast.makeText(PlayActivity.this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
                } else {
                    // Si la réponse est incorrecte, changer la couleur du bouton en rouge
                    // et trouver le bouton avec la réponse correcte et changer sa couleur en vert
                    clickedButton.setBackgroundColor(Color.RED);
                    for (Button button : answersButtons) {
                        if (button.getText().toString().equals(correctAnswer)) {
                            button.setBackgroundColor(Color.GREEN);
                            break;
                        }
                    }
                    // Afficher un message de mauvaise réponse
                    Toast.makeText(PlayActivity.this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
                }

                // Mettre une tempo d'1 seconde avant de réinitialiser la couleur des boutons
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (Button button : answersButtons) {
                            button.setBackgroundColor(couleurButton);
                        }
                        // prochaine question
                        mCalcul = mCalculList.getNextCalcul();
                        if (mCalcul == null ) {
                            //  plus de questions, afficher les résultats
                            showResults();
                            finish();
                        } else {
                            // Jouer la prochaine question
                            playCalcul();
                        }
                    }
                }, 1000);
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






