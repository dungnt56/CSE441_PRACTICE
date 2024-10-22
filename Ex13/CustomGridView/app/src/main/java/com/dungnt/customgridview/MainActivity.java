package com.dungnt.customgridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Image> imageList;
    MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid1);
        imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.img1, "Ảnh 1"));
        imageList.add(new Image(R.drawable.img2, "Ảnh 2"));

        adapter = new MyArrayAdapter(this, R.layout.list_item, imageList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("image", imageList.get(position).getImg());
                intent.putExtra("name", imageList.get(position).getName());
                startActivity(intent);
            }
        });
    }
}
