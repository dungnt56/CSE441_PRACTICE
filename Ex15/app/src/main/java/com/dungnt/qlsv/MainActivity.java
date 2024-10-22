package com.dungnt.qlsv;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edmalop, edtenlop, edsiso;
    Button btninsert, btndelete, btnupdate, btnquery;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        createOrOpenDatabase();
        addEvent();
    }

    private void addControl() {
        edmalop = findViewById(R.id.edmalop);
        edtenlop = findViewById(R.id.edtenlop);
        edsiso = findViewById(R.id.edsiso);

        btninsert = findViewById(R.id.btninsert);
        btndelete = findViewById(R.id.btndelete);
        btnupdate = findViewById(R.id.btnupdate);
        btnquery = findViewById(R.id.btnquery);

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);
    }

    private void createOrOpenDatabase() {
        mydatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE tblLop(malop TEXT PRIMARY KEY, tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table already exists");
        }
    }

    private void addEvent() {
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edmalop.getText().toString();
                String tenlop = edtenlop.getText().toString();
                int siso = Integer.parseInt(edsiso.getText().toString());

                ContentValues values = new ContentValues();
                values.put("malop", malop);
                values.put("tenlop", tenlop);
                values.put("siso", siso);

                long result = mydatabase.insert("tblLop", null, values);
                if (result == -1) {
                    Toast.makeText(MainActivity.this, "Fail to Insert Record", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insert Record Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edmalop.getText().toString();
                int result = mydatabase.delete("tblLop", "malop=?", new String[]{malop});

                if (result == 0) {
                    Toast.makeText(MainActivity.this, "No record to Delete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, result + " record is Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edmalop.getText().toString();
                String tenlop = edtenlop.getText().toString();
                int siso = Integer.parseInt(edsiso.getText().toString());

                ContentValues values = new ContentValues();
                values.put("tenlop", tenlop);
                values.put("siso", siso);

                int result = mydatabase.update("tblLop", values, "malop=?", new String[]{malop});
                if (result == 0) {
                    Toast.makeText(MainActivity.this, "No record to Update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, result + " record is Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor cursor = mydatabase.query("tblLop", null, null, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        String data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getInt(2);
                        mylist.add(data);
                    } while (cursor.moveToNext());

                    cursor.close();
                }
                myadapter.notifyDataSetChanged();
            }
        });
    }
}
