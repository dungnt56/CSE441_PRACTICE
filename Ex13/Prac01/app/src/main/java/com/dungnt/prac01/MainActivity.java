package com.dungnt.prac01;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = findViewById(R.id.selection);

        String[] arr = {"Hà Nội", "Huế", "Sài Gòn", "Hà Giang", "Hội An", "Kiên Giang", "Lâm Đồng", "Long Khánh"};

        singleComplete = findViewById(R.id.edtauto);
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        singleComplete.setAdapter(myadapter);

        multiComplete = findViewById(R.id.multiAutoCompleteTextView);
        multiComplete.setAdapter(myadapter);
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) { }
        });
    }
}
