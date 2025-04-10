package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Match with IDs in activity_main.xml
        EditText nameInput = findViewById(R.id.editTextName);
        Button btnStartQuiz = findViewById(R.id.buttonStartQuiz);
        Button btnCalculator = findViewById(R.id.buttonCalculator);

        // Restore saved name if any
        if (!userName.isEmpty()) {
            nameInput.setText(userName);
        }

        // Click: Start Quiz
        btnStartQuiz.setOnClickListener(v -> {
            // Save user name (trim white space)
            userName = nameInput.getText().toString().trim();
            // Move to QuizActivity
            Intent quizIntent = new Intent(this, QuizActivity.class);
            startActivity(quizIntent);
        });

        // Click: Open Calculator
        btnCalculator.setOnClickListener(v -> {
            startActivity(new Intent(this, CalculatorActivity.class));
        });
    }
}
