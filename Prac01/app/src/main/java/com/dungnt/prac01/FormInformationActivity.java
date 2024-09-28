package com.dungnt.prac01;

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

import java.util.HashMap;
import java.util.Map;

public class FormInformationActivity extends AppCompatActivity {

    EditText etStudentName, etGrade;
    Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_information);

        etStudentName = findViewById(R.id.et_student_name);
        etGrade = findViewById(R.id.et_student_grade);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();

                String studentName = etStudentName.getText().toString();
                String studentGrade = etGrade.getText().toString();

                returnIntent.putExtra("studentName", studentName);
                returnIntent.putExtra("studentGrade", studentGrade);
                setResult(33, returnIntent);
                finish();
            }
        });

    }
}