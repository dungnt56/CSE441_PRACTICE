package com.dungnt.ex03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnPlus, btnDivide, btnSub, btnMulti;
    EditText inputA, inputB, resultEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputA = findViewById(R.id.edta);
        inputB = findViewById(R.id.edtb);
        resultEditText = findViewById(R.id.edtc);

        btnDivide = findViewById(R.id.btnDivide);
        btnPlus = findViewById(R.id.btnPlus);
        btnMulti = findViewById(R.id.btnMulti);
        btnSub = findViewById(R.id.btnSub);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputAText = inputA.getText().toString().trim();
                String inputBText = inputB.getText().toString().trim();

                if (isInteger(inputAText) && isInteger(inputBText)) {
                    int a = Integer.parseInt(inputAText);
                    int b = Integer.parseInt(inputBText);
                    resultEditText.setText("a + b = " +(a+b));
                } else {
                    resultEditText.setText("Vui lòng nhập số nguyên hợp lệ.");
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputAText = inputA.getText().toString().trim();
                String inputBText = inputB.getText().toString().trim();

                if (isInteger(inputAText) && isInteger(inputBText)) {
                    int a = Integer.parseInt(inputAText);
                    int b = Integer.parseInt(inputBText);
                    resultEditText.setText("a - b = " +(a-b));
                } else {
                    resultEditText.setText("Vui lòng nhập số nguyên hợp lệ.");
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputAText = inputA.getText().toString().trim();
                String inputBText = inputB.getText().toString().trim();


                if (isInteger(inputAText) && isInteger(inputBText)) {
                    int a = Integer.parseInt(inputAText);
                    int b = Integer.parseInt(inputBText);

                    if (b == 0){
                        inputB.setError("Không thể nhập số 0. Vui lòng nhập số khác.");
                    }else {
                        resultEditText.setText("a / b = " +(Double.valueOf(a) /Double.valueOf(b)));
                    }
                } else {
                    resultEditText.setText("Vui lòng nhập số nguyên hợp lệ.");
                }
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputAText = inputA.getText().toString().trim();
                String inputBText = inputB.getText().toString().trim();

                if (isInteger(inputAText) && isInteger(inputBText)) {
                    int a = Integer.parseInt(inputAText);
                    int b = Integer.parseInt(inputBText);
                    resultEditText.setText("a * b = " +(a*b));
                } else {
                    resultEditText.setText("Vui lòng nhập số nguyên hợp lệ.");
                }

            }
        });
    }
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}