package com.dungnt.calendarnote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtwork = findViewById(R.id.edtwork);
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtminute);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.lv1);
        txtdate = findViewById(R.id.txtdate);

        arraywork = new ArrayList<>();
        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        lv.setAdapter(arrAdapter);

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate.getTime()));

        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
//                    String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();
                    String str = String.format("+ %s - %s:%s", edtwork.getText().toString(), edth.getText().toString(), edtm.getText().toString());
                    arraywork.add(str);
                    arrAdapter.notifyDataSetChanged();

                    edtwork.setText("");
                    edth.setText("");
                    edtm.setText("");
                }
            }
        });
    }
}
