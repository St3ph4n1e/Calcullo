package com.example.calcullo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartActivity extends AppCompatActivity implements View.OnClickListener  {

    CalculList easy = CalculGenerator.generateEasyCalculList();
    CalculList medium = CalculGenerator.generateMediumCalculList();
    CalculList difficult = CalculGenerator.generateDifficultCalculList();

    CalculList melange = CalculGenerator.generateSoDifficultCalculList();

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

        String[] difficultyLevels = {"Facile", "Moyen", "Difficile","Un peu de tout"};

        builder.setItems(difficultyLevels, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(StartActivity.this, PlayActivity.class);


                switch (which) {
                    case 0:
                        easy.getNextCalcul();
                        intent.putExtra("Calculs", easy);
                        break;

                    case 1:
                        medium.getNextCalcul();
                        intent.putExtra("Calculs", medium);
                        break;

                    case 2:
                        difficult.getNextCalcul();
                        intent.putExtra("Calculs", difficult);
                        break;

                    case 3:
                        melange.getNextCalcul();
                        intent.putExtra("Calculs", melange);
                        break;
                    default:

                        break;
                }

                startActivity(intent);
            }
        });
        builder.show();
    }


}