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
                int a = Integer.parseInt("0" + inputA.getText());
                int b = Integer.parseInt("0" + inputB.getText());
                resultEditText.setText("a + b = " +(a+b));
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0" + inputA.getText());
                int b = Integer.parseInt("0" + inputB.getText());
                resultEditText.setText("a - b = " +(a-b));

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0" + inputA.getText());
                int b = Integer.parseInt("0" + inputB.getText());
                if (b == 0){
                    inputB.setError("Không thể nhập số 0. Vui lòng nhập số khác.");
                }else {
                    resultEditText.setText("a / b = " +(a /b));
                }

            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0" + inputA.getText());
                int b = Integer.parseInt("0" + inputB.getText());
                resultEditText.setText("a * b = " +(a*b));

            }
        });
    }
}