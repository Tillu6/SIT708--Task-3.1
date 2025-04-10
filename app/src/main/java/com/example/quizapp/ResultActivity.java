package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView txtCongrats;
    private TextView txtResult;
    private Button btnRetry;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        txtCongrats = findViewById(R.id.txtCongrats);
        txtResult = findViewById(R.id.txtResult);
        btnRetry = findViewById(R.id.btnRetry);
        btnFinish = findViewById(R.id.btnFinish);

        // Get the score and total number of questions from the intent
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String userName = getIntent().getStringExtra("userName"); // Assuming you might pass the user's name

        // Display the result
        txtCongrats.setText(getString(R.string.congrats_user, userName != null ? userName : ""));
        txtResult.setText(getString(R.string.result_score_display, score, total));

        // Set up the "Take New Quiz" button listener
        btnRetry.setOnClickListener(v -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });

        // Set up the "Finish" button listener
        btnFinish.setOnClickListener(v -> finishAffinity());
    }
}