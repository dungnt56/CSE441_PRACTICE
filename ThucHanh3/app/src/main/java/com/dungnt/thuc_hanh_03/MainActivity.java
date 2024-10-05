package com.dungnt.thuc_hanh_03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private FloatingActionButton fabAddStudent;
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
        this.loadListStudent();

        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddStudent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intentAddStudent);
            }
        });
    }
    private void loadListStudent(){
        List<Student> studentList = loadStudentsFromJson();
        if (studentList != null) {
            studentAdapter = new StudentAdapter(MainActivity.this, studentList);
            recyclerView.setAdapter(studentAdapter);
        }
    }
    private List<Student> loadStudentsFromJson() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("students.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading JSON file", e);
            return null;
        }

        Gson gson = new Gson();
        Type studentListType = new TypeToken<List<Student>>() {}.getType();
        return gson.fromJson(json, studentListType);
    }
}