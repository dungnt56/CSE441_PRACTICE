package com.dungnt.ex04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculatorBmi;
    Button btnConvertTemporary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        btnCalculatorBmi = findViewById(R.id.btnCalculatorBMI);
        btnConvertTemporary = findViewById(R.id.btnConvertTemporary);

        btnConvertTemporary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(MainActivity.this, ConvertTemporary.class);
                startActivity(inten);

            }
        });

        btnCalculatorBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Open calculator BMI", Toast.LENGTH_SHORT).show();
            }
        });
    }
}