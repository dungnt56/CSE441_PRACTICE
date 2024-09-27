package com.dungnt.task3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private Button buttonSum, buttonDifference;
    private int valueA, valueB;
    private EditText etValueA, etValueB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        etValueA = findViewById(R.id.et_valueA);
        etValueB = findViewById(R.id.et_valueB);

        Intent intent = getIntent();
        valueA = intent.getIntExtra("valueA", 0);
        valueB = intent.getIntExtra("valueB", 0);

        etValueA.setText(String.valueOf(valueA));
        etValueB.setText(String.valueOf(valueB));

        buttonSum = findViewById(R.id.buttonSum);
        buttonDifference = findViewById(R.id.buttonDifference);

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = valueA + valueB;
                returnResult(result);
            }
        });

        buttonDifference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = valueA - valueB;
                returnResult(result);
            }
        });
    }

    private void returnResult(int result) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", result);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}