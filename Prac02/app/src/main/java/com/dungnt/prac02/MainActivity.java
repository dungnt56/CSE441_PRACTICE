package com.dungnt.prac02;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TEXT_DEFAULT = "No Result!";
    Button btnAddNewStaff;
    EditText etStaffId, etStaffFullName, etBirthDate, etSalary;
    TextView tvResult;
    List<Staff> listStaff = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnAddNewStaff = findViewById(R.id.btn_addNewStaff);
        etStaffId = findViewById(R.id.et_staffId);
        etStaffFullName = findViewById(R.id.et_fullname);
        etBirthDate = findViewById(R.id.et_birthDate);
        etSalary = findViewById(R.id.et_salary);
        tvResult = findViewById(R.id.textView);
        tvResult.setText(TEXT_DEFAULT);


        btnAddNewStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String staffId = etStaffId.getText().toString();
                String staffFullname = etStaffFullName.getText().toString();
                String birthDate = etBirthDate.getText().toString();
                String salary = etSalary.getText().toString();

                Staff newStaff = new Staff(staffId, staffFullname, birthDate, salary);
                listStaff.add(newStaff);

                StringBuilder staffDisplay = new StringBuilder();

                listStaff.forEach(staff -> {
                    if (!TextUtils.isEmpty(staffDisplay.toString())){
                        staffDisplay.append("\n");
                    }
                    staffDisplay.append(staff.getId()).append("-")
                            .append(staff.getFullname()).append("-")
                            .append(staff.getBirthDate()).append("-")
                            .append(staff.getSalary());
                });

                tvResult.setText(staffDisplay);

                etStaffId.requestFocus();
                etStaffId.setText("");
                etStaffFullName.setText("");
                etBirthDate.setText("");
                etSalary.setText("");
            }
        });

    }
}