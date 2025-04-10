package com.example.quizapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizapp.R;

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText val1 = findViewById(R.id.editVal1);
        EditText val2 = findViewById(R.id.editVal2);
        TextView result = findViewById(R.id.txtCalcResult);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(val1.getText().toString());
                int b = Integer.parseInt(val2.getText().toString());
                result.setText("Result: " + (a + b));
            } catch (NumberFormatException e) {
                result.setText("Invalid input");
            }
        });

        findViewById(R.id.btnSubtract).setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(val1.getText().toString());
                int b = Integer.parseInt(val2.getText().toString());
                result.setText("Result: " + (a - b));
            } catch (NumberFormatException e) {
                result.setText("Invalid input");
            }
        });
    }
}
