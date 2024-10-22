package com.dungnt.tabselector_customlistview;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv1, lv2, lv3;
    ArrayList<Item> list1, list2, list3;
    MyArrayAdapter myarray1, myarray2, myarray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        TabHost tab = findViewById(R.id.tabhost);
        tab.setup();

        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Tab 1");
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Tab 2");
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Tab 3");
        tab.addTab(tab3);

        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        list1.add(new Item("52300", "Em là ai Tôi là ai", 1));
        list1.add(new Item("52600", "Chén Đắng", 0));

        list2.add(new Item("57236", "Gọi em ở cuối sông hồng", 0));
        list2.add(new Item("51548", "Quê hương tuổi thơ tôi", 1));

        list3.add(new Item("57689", "Hát với dòng sông", 1));
        list3.add(new Item("58716", "Say tình _ Remix", 0));

        myarray1 = new MyArrayAdapter(this, R.layout.list_item, list1);
        myarray2 = new MyArrayAdapter(this, R.layout.list_item, list2);
        myarray3 = new MyArrayAdapter(this, R.layout.list_item, list3);

        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    private void addEvent() {
    }
}
