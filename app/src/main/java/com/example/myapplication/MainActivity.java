package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ImageView mQuizImage; // atrybuty klasy

    private String mAnswer;

    private int mScore = 0;

    private int mQuizNum = 1;

    private int QuestionNum = 0;

    private TextView mQuestionsView;

    private TextView mQuizNumView;

    private Questions mQuestions = new Questions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // metoda, która otwiera się po otwarciu nowej aktywności
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionsView = findViewById(R.id.question_textview); // przypisanie widoku z kodem javu
        mQuizNumView = findViewById(R.id.quiznum_textview); // przypisanie widoku z kodem javy

        updateQuestions();


        Button submit = findViewById(R.id.button_submit); // deklaracja przycisku
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { // metoda, ktora opisuje aktywnosc po nacisnieciu przycisku

                if(mQuestions.getType(QuestionNum) == "radiobutton") {
                    if (mQuestions.getCoorectAnswers(QuestionNum).equals(mAnswer)) {

                        mScore++;
                        displayToastCorrectAnswer(); // wyswietlenie Toastu
                    } else {
                        displayToastWrongAnswer();

                    }

                    Handler handler = new Handler(); // zmiana widoku po pytaniu
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() { // opoznienie wyswietlenia
                            updateQuestions();

                        }
                    }, 1000);

                }

                SystemClock.sleep(1000); // opoznienie dzialania nastepnych metod

                if(QuestionNum == mQuestions.getLength() -1){ // sprawdzenie czy to jest ostatnie pytanie

                    Intent intent_result = new Intent(MainActivity.this, ResultActivity.class);
                    intent_result.putExtra("totalQuestions",mQuestions.getLength());
                    intent_result.putExtra("finalScore", mScore);

                    startActivity(intent_result); // ta metoda uruchamia nam aktywnosc wyswietlajaca wynik

                    QuestionNum = 0;
                    mQuizNum = 0;
                    mScore = 0;
                }else {
                    QuestionNum++;
                    mQuizNum++;
                }
                updateQuestions();

            }
        });


    }

    private void displayToastCorrectAnswer(){ // wyswietlenie Toastu
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

    }
    private void displayToastWrongAnswer(){ // wyswietlenie Toastu
        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();

    }



    private void updateQuestions(){ /// ustalenie widoku w aktywności
        LinearLayout answer_layout = findViewById(R.id.answers_layout);
        answer_layout.removeAllViews();

        mAnswer="";

        mQuizNumView.setText(mQuizNum + "/" + String.valueOf(mQuestions.getLength())); // pokazuje nam który nr pytania

        mQuestionsView.setText(mQuestions.getQuestions(QuestionNum));

        if(mQuestions.getType(QuestionNum) == "radiobutton"){

            showRadioButtonAnswers(QuestionNum);
        }

        showMainImage();

        ScrollView sv = findViewById(R.id.scrollView);

        sv.smoothScrollTo(0, 0); // pozwala nam na przewijanie widoku
    }

    private void showMainImage(){ // przedstawienie flag

        mQuizImage = findViewById(R.id.quiz_image);
        String img = mQuestions.getImages(QuestionNum);
        mQuizImage.setImageResource(getResources().getIdentifier(img,"drawable", getPackageName())); // pobieramy zdjecie o okreslonej nazwie

    }

    private void showRadioButtonAnswers(int qnum){ // wyswietlenie odpowiedzi
        final LinearLayout answerLayout = findViewById(R.id.answers_layout);

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        rg.setLayoutParams(lp); //ustawienie wygladu dla odpowiedzi
        rg.setPadding(250, 0, 0, 0);

        final RadioButton[] rb1 = new RadioButton[3]; // definicja ilosci przyciskow

        for (int i =0;i<=2 ; i++){ // petla wykonujaca sie 3 razy, która ustawia atrybuty każdej odpowiedzi
            rb1[i] = new RadioButton(this);
            rb1[i].setText(mQuestions.getChoice(qnum)[i]);
            rb1[i].setTextColor(Color.BLACK);
            rb1[i].setPadding(8, 16, 8, 16);
            rb1[i].setTextSize(25);
            rb1[i].setId(i);
            rb1[i].setWidth(500);

            rg.addView(rb1[i]);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int Id) { // metoda systemowa, która opisuje działanie zaznaczenia odpowiedzi

                mAnswer = mQuestions.getChoice(QuestionNum)[Id];
            }
        });

        answerLayout.addView(rg); // dodanie przyciskow(radiobutton) do widoku
}
    }