package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView txtQuestion;
    private RadioGroup radioGroup;
    private Button btnSubmit;
    private ProgressBar progressBar;

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        // Load questions and display the first one
        questions = loadQuestions();
        loadNextQuestion();

        // Listener for the submit button
        btnSubmit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                // Optionally display a message to the user (e.g., using a Toast)
                return; // No option selected, so exit the listener
            }
            RadioButton selectedButton = findViewById(selectedId);
            int userChoiceIndex = radioGroup.indexOfChild(selectedButton);
            int correctIndex = questions.get(currentIndex).getCorrectAnswer();

            // Check the answer and change the text color accordingly
            if (userChoiceIndex == correctIndex) {
                selectedButton.setTextColor(Color.GREEN);
                score++;
            } else {
                selectedButton.setTextColor(Color.RED);
                // Mark the correct answer in green
                if (correctIndex < radioGroup.getChildCount()) {
                    RadioButton correctButton = (RadioButton) radioGroup.getChildAt(correctIndex);
                    correctButton.setTextColor(Color.GREEN);
                }
            }

            // Move to the next question or finish the quiz
            currentIndex++;
            if (currentIndex < questions.size()) {
                loadNextQuestion();
            } else {
                Intent resultIntent = new Intent(this, ResultActivity.class);
                resultIntent.putExtra("score", score);
                resultIntent.putExtra("total", questions.size());
                startActivity(resultIntent);
                finish();
            }
        });
    }

    /**
     * Loads the next question, resets RadioButton colors, updates the question text,
     * and sets the answer options.
     */
    private void loadNextQuestion() {
        // Reset the text color for all radio buttons
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setTextColor(Color.BLACK);
        }
        // Clear any previous selection
        radioGroup.clearCheck();

        // Load the current question and options
        Question q = questions.get(currentIndex);
        txtQuestion.setText(q.getQuestion());
        String[] opts = q.getOptions();
        for (int i = 0; i < opts.length && i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(opts[i]);
        }

        // Update the progress bar based on the current question
        int progress = (int) (((currentIndex + 1) / (float) questions.size()) * 100);
        progressBar.setProgress(progress);
    }

    /**
     * Loads questions into the quiz. Modify or add questions as needed.
     * @return A list of Question objects.
     */
    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Paris", "Rome", "Madrid"}, 1));
        list.add(new Question("5 + 3 = ?",
                new String[]{"6", "7", "8", "9"}, 2));
        return list;
    }
}