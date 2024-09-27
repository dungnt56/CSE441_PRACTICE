package com.dungnt.task3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnRequest;
    EditText etValueA, etValueB, etResult;
    private static final int REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnRequest = findViewById(R.id.btn_request);
        etValueA = findViewById(R.id.et_valueA);
        etValueB = findViewById(R.id.et_valueB);
        etResult = findViewById(R.id.et_result);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                int a = Integer.parseInt(etValueA.getText().toString());
                int b = Integer.parseInt(etValueB.getText().toString());

                resultIntent.putExtra("valueA", a);
                resultIntent.putExtra("valueB", b);
                startActivityForResult(resultIntent, REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int result = data.getIntExtra("result", 0);
            etResult.setText(String.valueOf(result));
        }
    }
}