package com.dungnt.thuc_hanh_03.activity;

import static com.dungnt.thuc_hanh_03.utils.Constant.REQUEST_CODE.REQUEST_ADD_STUDENT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dungnt.thuc_hanh_03.MainActivity;
import com.dungnt.thuc_hanh_03.R;
import com.dungnt.thuc_hanh_03.entity.Student;
import com.dungnt.thuc_hanh_03.helper.JsonHelper;
import com.dungnt.thuc_hanh_03.utils.Constant;
import androidx.appcompat.app.AlertDialog;

public class StudentDetails extends AppCompatActivity {

    TextView tvId, tvFullname, tvGender, tvBirthDate, tvEmail, tvAddress, tvMajor, tvGpa, tvYear;
    int position;
    Student studentDetail;


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
        studentDetail = student;
        position = i.getIntExtra("position", -1);

        tvId.setText(student.getId());
        tvFullname.setText(student.getFullnameStudent());
        tvGender.setText(student.getGender());
        tvBirthDate.setText(student.getBirthDate());
        tvEmail.setText(student.getEmail());
        tvAddress.setText(student.getAddress());
        tvMajor.setText(student.getMajor());
        tvGpa.setText(String.valueOf(student.getGpa()));
        tvYear.setText(String.valueOf(student.getYear()));


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(student.getId());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else if (item.getItemId() == R.id.action_edit){
            Intent intentUpdateStudent = new Intent(StudentDetails.this, AddStudent.class);
            intentUpdateStudent.putExtra("studentInfo", studentDetail);
            intentUpdateStudent.putExtra("position", position);
            startActivityForResult(intentUpdateStudent, Constant.REQUEST_UPDATE);
            return true;

        }else if (item.getItemId() == R.id.action_delete){
            this.showConfirmDialog();
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constant.REQUEST_UPDATE == requestCode && Activity.RESULT_OK == resultCode){
            Student studentUpdate = (Student) data.getExtras().getSerializable("studentUpdate");
            tvId.setText(studentUpdate.getId());
            tvFullname.setText(studentUpdate.getFullnameStudent());
            tvGender.setText(studentUpdate.getGender());
            tvBirthDate.setText(studentUpdate.getBirthDate());
            tvEmail.setText(studentUpdate.getEmail());
            tvAddress.setText(studentUpdate.getAddress());
            tvMajor.setText(studentUpdate.getMajor());
            tvGpa.setText(String.valueOf(studentUpdate.getGpa()));
            tvYear.setText(String.valueOf(studentUpdate.getYear()));
        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có chắc chắn muốn xóa?");

        builder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", position);
            setResult(Constant.REQUEST_DELETE, resultIntent);
            finish();
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}