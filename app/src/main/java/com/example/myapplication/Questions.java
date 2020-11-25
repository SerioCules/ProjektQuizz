package com.example.myapplication;

public class Questions {


    private String mQuestions[] = { // pytania

            "What is the flag?",
            "Guess the flag",
            "Do you know this one?",
            "Tell me which one",
            "This national flag is:",
            "Tell me which one",
            "This national flag is:",
            "Do you know this one?",
            "Guess the flag",
            "What is the flag?"


    };

    private String mChoice[] [] = { // wybory do odpowiedzi

            {"Germany", "Finland", "Egypt"},
            {"Mexico", "France", "England"},
            {"USA", "Australia", "China"},
            {"San Marino", "New Zealand", "Poland"},
            {"Kenya", "Tunisia", "Barbados"},
            {"Madagascar", "Sri Lanka", "Saint Martin"},
            {"Algeria", "Australia", "Cayman Islands" },
            {"Spain", "Japan", "Catalonia"},
            {"Papua new Guinea", "India", "Turkey"},
            {"Sweden", "Finland", "Denmark"}

    };
    private String mImages [] = { // oznaczenia naszych zdjec
            "q1", //Egypt
            "q2", //France
            "q3", //USA
            "q4", //San Marino
            "q5", //Barbados
            "q6", //Sri Lanka
            "q7", // Kajmany
            "q8", //Katalonia
            "q9", //Papu nowa Gwinea
            "q10", //Finalnd
    };

    private String mQuestionsType []= { // definicja typu odpowiedzi
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",


    };

    private String mCoorectAnswers [] = { // prawidlowe odpowiedzi
            "Egypt",
            "France",
            "USA",
            "San Marino",
            "Barbados",
            "Sri Lanka",
            "Cayman Islands",
            "Catalonia",
            "Papua new Guinea",
            "Finland"
    };

    public String getQuestions(int q) { //pobranie pytania na określanej pozycji
        String question = mQuestions[q];
        return question;
    }

    public String[] getChoice(int q) { // pobranie odpowiedzi do pytania
        String[] choice = mChoice[q];
        return choice;
    }

    public String getImages(int q) { // pobranie nazwy zdjec do pytan
        String img = mImages[q];
        return img;
    }

    public String getType(int q) { // pobieranie typu pytania
        String type = mQuestionsType[q];
        return type;
    }

    public int getLength(){
        return mQuestions.length;
    } // pobieranie ilosc pytan

    public String getCoorectAnswers(int q) { // pobieranie wlasciwe odpowiedzi

        String correct = mCoorectAnswers[q];

        return correct;
    }
}
