package com.dungnt.thuc_hanh_03.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dungnt.thuc_hanh_03.R;
import com.dungnt.thuc_hanh_03.entity.Student;

public class StudentDetails extends AppCompatActivity {

    TextView tvId, tvFullname, tvGender, tvBirthDate, tvEmail, tvAddress, tvMajor, tvGpa, tvYear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        tvId = findViewById(R.id.tv_id);
        tvFullname = findViewById(R.id.tv_fullName);
        tvGender = findViewById(R.id.tv_gender);
        tvBirthDate = findViewById(R.id.tv_birthDate);
        tvEmail = findViewById(R.id.tv_email);
        tvAddress = findViewById(R.id.tv_address);
        tvMajor = findViewById(R.id.tv_major);
        tvGpa = findViewById(R.id.tv_gpa);
        tvYear = findViewById(R.id.tv_year);

        Intent i = getIntent();
        Student student = (Student)i.getSerializableExtra("studentInfo");

        tvId.setText(student.getId());
        tvFullname.setText(student.getFullnameStudent());
        tvGender.setText(student.getGender());
        tvBirthDate.setText(student.getBirthDate());
        tvEmail.setText(student.getEmail());
        tvAddress.setText(student.getAddress());
        tvMajor.setText(student.getMajor());
        tvGpa.setText(String.valueOf(student.getGpa()));
        tvYear.setText(String.valueOf(student.getYear()));

    }
}