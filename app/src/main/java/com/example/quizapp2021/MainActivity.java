
package com.example.quizapp2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
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
    }
}