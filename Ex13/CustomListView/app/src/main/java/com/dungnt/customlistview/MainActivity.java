package com.dungnt.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        String[] namePhone = {"Điện thoại Iphone 12", "Điện thoại SamSung S20", "Điện thoại Nokia 6", "Điện thoại Bphone 2020", "Điện thoại Oppo 5", "Điện thoại VSmart joy2"};
        int[] imagePhone = {R.drawable.ip, R.drawable.samsung, R.drawable.nokia, R.drawable.bphone, R.drawable.oppo, R.drawable.vsmart};

        ArrayList<Phone> listPhone = new ArrayList<>();
        for (int i = 0; i < namePhone.length; i++) {
            listPhone.add(new Phone(namePhone[i], imagePhone[i]));
        }

        ListPhoneAdapter phoneAdapter = new ListPhoneAdapter(this, R.layout.item_list_phone, listPhone);
        ListView lv = findViewById(R.id.lv_phones);
        lv.setAdapter(phoneAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("name", namePhone[position]);
                startActivity(intent);
            }
        });
    }
}