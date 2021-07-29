
package com.example.quizapp2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;

public class MainActivity extends AppCompatActivity {
    private static Button english, hindi, french, german, russian, luganda;
    private static TextView chooseText;
    private static Locale myLocale;

    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    TextView questionTV;
    Button trueButton;
    Button falseButton;
    Button nextButton;
    Toast toast;
    CharSequence text = "Correct";
    int score;

    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;

    int currentQindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTV = (TextView)findViewById(R.id.QuestionTV);
        trueButton = (Button)findViewById(R.id.TrueButton);
        falseButton = (Button)findViewById(R.id.FalseButton);
        nextButton = (Button) findViewById(R.id.NextButton);
        score = 0;
        currentQindex = 0;
        /// create the questions!
        q1 = new Question("Buganda Kingdom is located in USA.", false);
        q2 = new Question("Buganda Kingdom was transformed into Uganda.", true);
        q3 = new Question("Buganda Kingdom was ruled by 34 Kings.", true);
        q4 = new Question("Kabaka Mutebi is the current King of Buganda.", true);
        q5 = new Question("54 clans of Baganda people speak Luganda.", true);
         // Create Array and init it!
        questions = new Question[] {q1, q2, q3,q4,q5};
        currentQ = questions[currentQindex];



        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentQ.getCorrectAnswer() == true) {
                     //text = "CORRECT";
                    text = getString(R.string.correctMessage);
                    score +=1;
                }
                else
                    text = getString(R.string.incorrectMessage);
                Context context = getApplicationContext();
                //CharSequence text = "Nice!";
                int duration = Toast.LENGTH_SHORT;
                //score += 1;

                 toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (currentQ.getCorrectAnswer() == false) {
                    text = getString(R.string.correctMessage);
                    score +=1;
                }
                else
                    {
                    text = getString(R.string.incorrectMessage);
                }
                Context context = getApplicationContext();
                //CharSequence text = "Sorry, try again!";
                int duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentQindex < questions.length -1){
                    currentQindex += 1;
                    currentQ = questions[currentQindex];
                    questionTV.setText(currentQ.getQuestionText());
                }

                else
                    {

                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra("scoreLBL", score);
                startActivity(intent);
                }
            }
        });

        public void changeLocale(String String lang;
        lang;
        lang) {
            if (lang.equalsIgnoreCase(""))
                return;
            Locale myLocale = new Locale(lang);//Set Selected Locale
            Locale.setDefault(myLocale);//set new locale as default
            Configuration config = new Configuration();//get Configuration
            config.locale = myLocale;//set config locale as selected locale
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initViews();
            setListeners();
            loadLocale();
        }


        //Initiate all views
        private void initViews() {
            sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            chooseText = (TextView) findViewById(R.id.choose_text);
            english = (Button) findViewById(R.id.english);
            hindi = (Button) findViewById(R.id.hindi);
            french = (Button) findViewById(R.id.french);
            german = (Button) findViewById(R.id.german);
            russian = (Button) findViewById(R.id.russian);
        }

        //Set Click Listener
        private void setListeners() {
            english.setOnClickListener(this);
            hindi.setOnClickListener(this);
            french.setOnClickListener(this);
            german.setOnClickListener(this);
            russian.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            String lang = "en";//Default Language
            switch (view.getId()) {
                case R.id.english:
                    lang = "en";
                    break;
                case R.id.hindi:
                    lang = "hi";
                    break;
                case R.id.french:
                    lang = "fr";
                    break;
                case R.id.german:
                    lang = "de";
                    break;
                case R.id.russian:
                    lang = "ru";
                    break;
                case R.id.luganda:
                    lang = "lg";
                    break;
            }

            changeLocale(lang);//Change Locale on selection basis
        }


        //Change Locale
        public void changeLocale(String lang) {
            if (lang.equalsIgnoreCase(""))
                return;
            myLocale = new Locale(lang);//Set Selected Locale
            saveLocale(lang);//Save the selected locale
            Locale.setDefault(myLocale);//set new locale as default
            Configuration config = new Configuration();//get Configuration
            config.locale = myLocale;//set config locale as selected locale
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
            updateTexts();//Update texts according to locale
        }

        //Save locale method in preferences
        public void saveLocale(String lang) {
            editor.putString(Locale_KeyValue, lang);
            editor.commit();
        }

        //Get locale method in preferences
        public void loadLocale() {
            String language = sharedPreferences.getString(Locale_KeyValue, "");
            changeLocale(language);
        }

        //Update text methods
        private void updateTexts() {
            chooseText.setText(R.string.tap_text);
            english.setText(R.string.btn_en);
            hindi.setText(R.string.btn_hi);
            russian.setText(R.string.btn_ru);
            french.setText(R.string.btn_fr);
            german.setText(R.string.btn_de);
        }


    }


    }
}