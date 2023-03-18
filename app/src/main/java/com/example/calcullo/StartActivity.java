package com.example.calcullo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener  {

    Calcul calcul;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == startButton) {
            showDifficultyDialog();
        }
    }




    private void showDifficultyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisissez la difficulté");

        String[] difficultyLevels = {"Facile", "Moyen", "Difficile"};

        builder.setItems(difficultyLevels, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(StartActivity.this, PlayActivity.class);


                switch (which) {
                    case 0:
                        calcul = CalculGenerator.easyCalcul();


                        break;
                    case 1:
                        calcul = CalculGenerator.mediumCalcul();


                        break;
                    case 2:
                        calcul = CalculGenerator.difficultCalcul();


                        break;
                    default:

                        break;
                }

                intent.putExtra("calcul", calcul);
                startActivity(intent);

            }
        });
        builder.show();
    }


}