package com.dungnt.thuc_hanh_03.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dungnt.thuc_hanh_03.R;
import com.dungnt.thuc_hanh_03.entity.Student;
import com.dungnt.thuc_hanh_03.helper.JsonHelper;
import com.dungnt.thuc_hanh_03.utils.Constant;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddStudent extends AppCompatActivity {

    private EditText editTextStudentId, editTextName, editTextEmail, editTextBirthdate, editTextGpa;
    private Spinner spinnerMajor, spinnerAddress, spinnerYear;
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale, radioOther;
    private Button buttonComplete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextBirthdate = findViewById(R.id.editTextBirthdate);
        editTextGpa = findViewById(R.id.editTextGpa);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        spinnerAddress = findViewById(R.id.spinnerAddress);
        spinnerYear = findViewById(R.id.spinnerYear);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);
        buttonComplete = findViewById(R.id.buttonComplete);

        buttonComplete.setOnClickListener(v -> {
            String studentId = editTextStudentId.getText().toString();
            String fullname = editTextName.getText().toString();
            String birthdate = editTextBirthdate.getText().toString();
            String address = spinnerAddress.getSelectedItem().toString();
            String year = spinnerYear.getSelectedItem().toString();
            String email = editTextEmail.getText().toString();
            String major = spinnerMajor.getSelectedItem().toString();
            String gpa = editTextGpa.getText().toString();
            String gender = "";
            int selectedId = radioGroupGender.getCheckedRadioButtonId();

            if (selectedId == radioMale.getId()) {
                gender = "Nam";
            } else if (selectedId == radioFemale.getId()) {
                gender = "Nữ";
            } else if (selectedId == radioOther.getId()) {
                gender = "Khác";
            }

            Boolean formIsValid = true;
            if (TextUtils.isEmpty(studentId)){
                editTextStudentId.setError("Mã sinh viên không được để trống");
                formIsValid = false;
            }
            if (TextUtils.isEmpty(fullname)){
                editTextName.setError("Họ và tên không được để trống");
                formIsValid = false;
            }
            if (TextUtils.isEmpty(birthdate)){
                editTextBirthdate.setError("Ngày sinh không được để trống");
                formIsValid = false;
            }
            if (!isValidDate(birthdate)){
                editTextBirthdate.setError("Ngày sinh không hợp lệ");
                formIsValid = false;
            }
            if (TextUtils.isEmpty(address)){
                Toast.makeText(AddStudent.this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
                formIsValid = false;
            }
            if (TextUtils.isEmpty(email)){
                editTextEmail.setError("Email không được để trống");
                formIsValid = false;
            }
            if (!isValidEmail(email)){
                editTextEmail.setError("Email không hợp lệ");
                formIsValid = false;
            }
            if (TextUtils.isEmpty(major)){
                Toast.makeText(AddStudent.this, "Chuyên ngành không được để trống", Toast.LENGTH_SHORT).show();
                formIsValid = false;
            }
            if (TextUtils.isEmpty(gpa)){
                editTextGpa.setError("Gpa không được để trống");
                formIsValid = false;
            }
            if (TextUtils.isEmpty(gender)){
                Toast.makeText(AddStudent.this, "Giới tính không được để trống", Toast.LENGTH_SHORT).show();
                formIsValid = false;
            }
            if (Boolean.FALSE.equals(formIsValid)) return;

            Toast.makeText(AddStudent.this, "Thông tin sinh viên đã được lưu.", Toast.LENGTH_SHORT).show();

            Student student = new Student();
            student.setId(studentId);
            student.setFullName(fullname);
            student.setBirthDate(birthdate);
            student.setAddress(address);
            student.setEmail(email);
            student.setMajor(major);
            student.setGpa(Float.valueOf(gpa));
            student.setYear(Integer.valueOf(year));
            student.setGender(gender);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newStudent", student);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}