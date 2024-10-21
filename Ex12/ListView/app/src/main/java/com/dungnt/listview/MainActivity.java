package com.dungnt.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtl = findViewById(R.id.selection);

        String[] arr1 = {"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9",
                "Sony Xperia XZ", "HTC One E9"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr1);
        ListView lv1 = findViewById(R.id.lv1);
        lv1.setAdapter(adapter1);

        lv1.setOnItemClickListener((arg0, arg1, i, arg3) -> txtl.setText("Vị trí " + i + " : " + arr1[i]));
    }
}