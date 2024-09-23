package com.dungnt.ex04;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConvertTemporary extends AppCompatActivity {

    private EditText editTextFahrenheit;
    private EditText editTextCelsius;
    private Button buttonConvertToCelsius;
    private Button buttonConvertToFahrenheit;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_convert_temporary);
        buttonConvertToCelsius = findViewById(R.id.buttonConvertToCelsius);
        buttonConvertToFahrenheit = findViewById(R.id.buttonConvertToFahrenheit);
        editTextCelsius = findViewById(R.id.editTextCelsius);
        editTextFahrenheit = findViewById(R.id.editTextFahrenheit);
        buttonClear = findViewById(R.id.buttonClear);

        buttonConvertToCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToCelsius();
            }
        });

        buttonConvertToFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToFahrenheit();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void convertToCelsius() {
        String fahrenheit = editTextFahrenheit.getText().toString();
        if (!TextUtils.isEmpty(fahrenheit)) {
            double f = Double.parseDouble(fahrenheit);
            double c = (f - 32) * 5 / 9;
            editTextCelsius.setText(String.format("%.2f", c));
        } else {
            editTextFahrenheit.setError("Nhap gia tri Fahrenheit");
//            Toast.makeText(this, "Nhap gia tri Fahrenheit", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertToFahrenheit() {
        String celsius = editTextCelsius.getText().toString();
        if (!TextUtils.isEmpty(celsius)) {
            double c = Double.parseDouble(celsius);
            double f = (c * 9 / 5) + 32;
            editTextFahrenheit.setText(String.format("%.2f", f));
        } else {
            editTextCelsius.setError("Nhap gia tri Celsius");
        }
    }

    private void clearFields() {
        editTextFahrenheit.setText("");
        editTextCelsius.setText("");
    }
}