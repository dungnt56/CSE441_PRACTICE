package com.dungnt.prac02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//    String[] arr = {
//            "Ipad", "Iphone", "New Ipad", "SamSung", "Nokia", "Sony Ericson",
//            "LG", "Q-Mobile", "HTC", "Blackberry", "G Phone", "FPT - Phone", "HK Phone"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final TextView selection = findViewById(R.id.selection);
//
//        GridView gv = findViewById(R.id.gridView);
//        ArrayAdapter<String> da = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
//        gv.setAdapter(da);
//
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selection.setText(arr[position]);
//            }
//        });
//    }


    Integer[] imageIDs = {
            R.drawable.ipad, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image2,
            R.drawable.image3, R.drawable.image4, R.drawable.image5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this, imageIDs));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                intent.putExtra("imageID", imageIDs[position]);
                startActivity(intent);
            }
        });
    }
}
