package com.dungnt.prac01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnOpenForm;
    TextView tvResultInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnOpenForm = findViewById(R.id.btn_openForm);
        tvResultInfo = findViewById(R.id.tv_resultInfo);

        btnOpenForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormInformationActivity.class);
                startActivityForResult(intent, 99);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33 && data != null) {
            String studentName = data.getStringExtra("studentName");
            String studentGrade = data.getStringExtra("studentGrade");

            StringBuilder result = new StringBuilder();
            if (!TextUtils.isEmpty(studentName)){
                result.append("Họ và tên: ").append(studentName);
            }
            if (!TextUtils.isEmpty(studentGrade)){
                result.append("\n");
                result.append("Điểm GPA: ").append(studentGrade);
            }

            tvResultInfo.setText(result);
        }
    }
}