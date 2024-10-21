package com.dungnt.prac02;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        // Nhận ID của hình ảnh từ Intent
        int imageID = getIntent().getIntExtra("imageID", 0);

        // Hiển thị hình ảnh phóng to
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(imageID);

        // Xử lý nút Back để quay lại
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Đóng Activity và quay lại GridView
            }
        });
    }
}
