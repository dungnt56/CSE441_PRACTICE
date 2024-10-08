package com.dungnt.thuc_hanh_03;

import static com.dungnt.thuc_hanh_03.utils.Constant.REQUEST_CODE.REQUEST_ADD_STUDENT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    Boolean asc = true;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private FloatingActionButton fabAddStudent;
    List<Student> studentList = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    @SuppressLint("MissingInflatedId")
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

        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddStudent = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(intentAddStudent, REQUEST_ADD_STUDENT);
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Constant.REQUEST_DELETE) {
                        Intent data = result.getData();
                        int position = data.getIntExtra("position", -1);
                        if (position != -1){
                            this.studentList.remove(position);
                            JsonHelper.saveDataToJson(this, Constant.FILE_JSON_LIST_STUDENTS, studentList);
                            this.loadListStudent();
                        }
                    }
                }
        );
        this.loadListStudent();
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách SV");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String textSearch) {
                searchStudentByName(textSearch);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textSearch) {
                searchStudentByName(textSearch);
                return true;
            }
        });

        return true;
    }
    private void searchStudentByName(String textSearch){
        List<Student> listSearchStudent = new ArrayList<>();
        if (TextUtils.isEmpty(textSearch)) {
            listSearchStudent.addAll(studentList);
        } else {
            String formatTextSearch = textSearch.toString().toLowerCase().trim();
            for (Student student : studentList) {
                if (student.getFullName().getFirstName().toLowerCase().contains(formatTextSearch) ||
                        student.getFullName().getLastName().toLowerCase().contains(formatTextSearch) ||
                        student.getFullName().getMiddleName().toLowerCase().contains(formatTextSearch) ||
                        student.getFullnameStudent().toLowerCase().contains(formatTextSearch)
                ) {
                    listSearchStudent.add(student);
                }
            }
        }
        loadListStudent(listSearchStudent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search){
            Toast.makeText(this, "action_search", Toast.LENGTH_SHORT).show();
            return true;

        }else if (item.getItemId() == R.id.sort_by_grade){
            Toast.makeText(this, "sort_by_grade", Toast.LENGTH_SHORT).show();
            this.sortStudentListByGrade();
            return true;
        }else if (item.getItemId() == R.id.sort_by_student_code){
            Toast.makeText(this, "sort_by_student_code", Toast.LENGTH_SHORT).show();
            this.sortStudentListByStudentCode();
            return true;
        }else if (item.getItemId() == R.id.sort_by_name){
            Toast.makeText(this, "sort_by_name", Toast.LENGTH_SHORT).show();
            this.sortStudentListByName();
            return true;
        }
        return false;
    }

    private void sortStudentListByGrade() {
        if (asc){
            studentList.sort((s1, s2) -> Double.compare(s1.getGpa(), s2.getGpa()));
        }else {
            studentList.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        }
        asc = !asc;
        loadListStudent();
    }

    private void sortStudentListByStudentCode() {
        if (asc){
            studentList.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
        }else {
            studentList.sort((s1, s2) -> s2.getId().compareTo(s1.getId()));
        }
        asc = !asc;
        loadListStudent();
    }

    private void sortStudentListByName() {
        if (asc){
            studentList.sort((s1, s2) -> s1.getFullnameStudent().compareTo(s2.getFullnameStudent()));
        }else {
            studentList.sort((s1, s2) -> s2.getFullnameStudent().compareTo(s1.getFullnameStudent()));
        }
        asc = !asc;
        loadListStudent();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD_STUDENT == requestCode && Activity.RESULT_OK == resultCode){
//            studentList
            Student newStudent = (Student) data.getExtras().getSerializable("newStudent");
            studentList.add(newStudent);
            JsonHelper.saveDataToJson(this, Constant.FILE_JSON_LIST_STUDENTS, studentList);
            this.loadListStudent();
        }
    }

    private void loadListStudent(List<Student> students){
        if (Objects.nonNull(students)){
            studentAdapter = new StudentAdapter(MainActivity.this, activityResultLauncher, students);
            recyclerView.setAdapter(studentAdapter);
        }else if (studentList != null) {
            studentAdapter = new StudentAdapter(MainActivity.this, activityResultLauncher, studentList);
            recyclerView.setAdapter(studentAdapter);
        }
    }
    private void loadListStudent(){
        if (studentList != null) {
            studentAdapter = new StudentAdapter(MainActivity.this, activityResultLauncher, studentList);
            recyclerView.setAdapter(studentAdapter);
        }
    }
}