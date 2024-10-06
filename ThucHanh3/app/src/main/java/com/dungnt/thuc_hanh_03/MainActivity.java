package com.dungnt.thuc_hanh_03;

import static com.dungnt.thuc_hanh_03.utils.Constant.REQUEST_CODE.REQUEST_ADD_STUDENT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dungnt.thuc_hanh_03.activity.AddStudent;
import com.dungnt.thuc_hanh_03.activity.StudentDetails;
import com.dungnt.thuc_hanh_03.adapter.StudentAdapter;
import com.dungnt.thuc_hanh_03.entity.Student;
import com.dungnt.thuc_hanh_03.helper.JsonHelper;
import com.dungnt.thuc_hanh_03.utils.Constant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private FloatingActionButton fabAddStudent;
    List<Student> studentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fabAddStudent = findViewById(R.id.fab_addStudent);
        recyclerView = findViewById(R.id.rcv_listStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Type studentListType = new TypeToken<List<Student>>() {}.getType();
        studentList = JsonHelper.loadDataFromJson(this, Constant.FILE_JSON_LIST_STUDENTS, studentListType);
        this.loadListStudent();

        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddStudent = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(intentAddStudent, REQUEST_ADD_STUDENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD_STUDENT == requestCode && Activity.RESULT_OK == resultCode){
//            studentList
            Student newStudent = (Student) data.getExtras().getSerializable("newStudent");
            studentList.add(newStudent);
            this.loadListStudent();
        }
    }

    private void loadListStudent(){
        if (studentList != null) {
            studentAdapter = new StudentAdapter(MainActivity.this, studentList);
            recyclerView.setAdapter(studentAdapter);
        }
    }
}