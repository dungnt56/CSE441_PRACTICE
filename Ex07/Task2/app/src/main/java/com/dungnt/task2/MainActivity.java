package com.dungnt.task2;

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

public class MainActivity extends AppCompatActivity {

    Button btnResult;
    EditText etValueA, etValueB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btn_result);
        etValueA = findViewById(R.id.et_valueA);
        etValueB = findViewById(R.id.et_valueB);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle body = new Bundle();
                int a = Integer.parseInt(etValueA.getText()+"");
                int b = Integer.parseInt(etValueB.getText()+"");
                body.putInt("valueA", a);
                body.putInt("valueB",b);
                resultIntent.putExtra("body", body);
                startActivity(resultIntent);

            }
        });

    }
}