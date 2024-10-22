package com.dungnt.customgridview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    ImageView imgView;
    TextView txtView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        imgView = findViewById(R.id.imageView2);
        txtView = findViewById(R.id.textView2);

        int image = getIntent().getIntExtra("image", 0);
        String name = getIntent().getStringExtra("name");

        imgView.setImageResource(image);
        txtView.setText(name);
    }
}
