package com.dungnt.ex04;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorBmi extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Button buttonCalculateBMI;
    private TextView textViewBMI;
    private TextView textViewDiagnosis;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator_bmi);

        editTextName = findViewById(R.id.editTextName);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculateBMI = findViewById(R.id.buttonCalculateBMI);
        textViewBMI = findViewById(R.id.textViewBMI);
        textViewDiagnosis = findViewById(R.id.textViewDiagnosis);

        buttonCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();

            }
        });
    }

    private void calculateBMI() {
        String heightValue = editTextHeight.getText().toString();
        String weightValue = editTextWeight.getText().toString();

        Boolean errorValue = false;
        if (TextUtils.isEmpty(heightValue)){
            editTextHeight.setError("Height not empty");
            errorValue = true;
        }
        if (TextUtils.isEmpty(weightValue)){
            editTextWeight.setError("Width not empty");
            errorValue = true;
        }
        if (!isValidNumber(heightValue)){
            editTextHeight.setError("Height not valid");
            errorValue = true;
        }
        if (!isValidNumber(weightValue)){
            editTextWeight.setError("Weight not valid");
            errorValue = true;
        }

        if (Boolean.TRUE.equals(errorValue)){
            return;
        }
        Double zeroValue = 0D;
        if (zeroValue.equals(Double.parseDouble(heightValue))){
            editTextHeight.setError("Height not valid");
            return;
        }

        double height = Double.parseDouble(heightValue) / 100;
        double weight = Double.parseDouble(weightValue);

        double bmi = weight / (height * height);

        textViewBMI.setText(String.format("BMI = %.2f", bmi));

        String diagnosis;
        if (bmi < 18) {
            diagnosis = "Gay'";
        } else if (bmi < 24.9) {
            diagnosis = "Binh thuong";
        } else if (bmi < 29.9) {
            diagnosis = "Beo phi do 1";
        } else if (bmi < 34.9){
            diagnosis = "Beo phi do 2";
        } else {
            diagnosis = "Beo phi do 3";
        }
        textViewDiagnosis.setText("Chuẩn đoán: " + diagnosis);
    }
    public Boolean isValidNumber(String value){

        if (TextUtils.isEmpty(value)) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}