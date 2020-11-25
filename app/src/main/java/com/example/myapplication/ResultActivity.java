package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView txtCorrectText;
    TextView txtPercentText;
    private int totalQuestions;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // metoda systemowa, ktora uruchamia aktywnosc
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result); // przypisanie pliku widoku do kodu Javy

        txtCorrectText = findViewById(R.id.correct_textview); // definiujemy teksty, laczymy widok z kodem
        txtPercentText = findViewById(R.id.percent_textview);


        Intent intent = getIntent(); // pobieramy informacje z MainActivity
        totalQuestions = intent.getIntExtra("totalQuestions", 0);
        finalScore = intent.getIntExtra("finalScore", 0); // domyslnie ustawione final score na 0

        int mPercentScore = finalScore * 100 / totalQuestions; // procenty do koncowych odpowiedzi
        txtPercentText.setText(String.format("%s%%", (Integer.valueOf(mPercentScore))));

        String final_Score_Text = getString(R.string.txtCorrectScore,finalScore,totalQuestions); // Koncowa tresc po wybraniu wszystkich odpowiedzi

        txtCorrectText.setText(final_Score_Text);
    }

    public void restartGame(View view) { // wracamy do poprzedniej uzywanej aktywnosci

        super.onBackPressed();
    }
}