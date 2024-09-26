package com.dungnt.ex05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class ConvertYear extends AppCompatActivity {

    public static final Map<Integer, String> MAP_CAN;

    static {
        MAP_CAN = new HashMap<>();
        MAP_CAN.put(0, "Canh");
        MAP_CAN.put(1, "Tân");
        MAP_CAN.put(2, "Nhâm");
        MAP_CAN.put(3, "Quý");
        MAP_CAN.put(4, "Giáp");
        MAP_CAN.put(5, "Ất");
        MAP_CAN.put(6, "Bính");
        MAP_CAN.put(7, "Đinh");
        MAP_CAN.put(8, "Mậu");
        MAP_CAN.put(9, "Kỷ");
    }

    public static final Map<Integer, String> MAP_CHI;
    static {
        MAP_CHI = new HashMap<>();
        MAP_CHI.put(0, "Thân");
        MAP_CHI.put(1, "Dậu");
        MAP_CHI.put(2, "Tuất");
        MAP_CHI.put(3, "Hợi");
        MAP_CHI.put(4, "Tý");
        MAP_CHI.put(5, "Sửu");
        MAP_CHI.put(6, "Dần");
        MAP_CHI.put(7, "Mẹo");
        MAP_CHI.put(8, "Thìn");
        MAP_CHI.put(9, "Tỵ");
        MAP_CHI.put(10, "Ngọ");
        MAP_CHI.put(11, "Mùi");
    }
    Button convertBtn;
    EditText edt_OriginYear;
    TextView resultConvertYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_convert_year);

        resultConvertYear = findViewById(R.id.tv_resultConvertYear);
        convertBtn = findViewById(R.id.convert);
        edt_OriginYear = findViewById(R.id.originYear);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originYear = edt_OriginYear.getText().toString();
                String resultConvert = convertYearNumberToChinaYear(originYear);
                resultConvertYear.setText(resultConvert);
            }
        });
    }

    private String convertYearNumberToChinaYear(String yearNumber){
        if (!isValidYear(yearNumber)){
            return "Nam khong hop le";
        }
        Integer yearValue = Integer.parseInt(yearNumber);
        String can = MAP_CAN.get(yearValue % 10);
        String am = MAP_CHI.get(yearValue % 12);
        return can + " " + am;
    }

    /*
        * Description: check valid year number
        * Condition:
            - only has number character
            - Number of character is 4
            - Bigger than 0 and smaller or equal 9999
    */
    public boolean isValidYear(String year) {
        if (year == null || year.length() != 4 || !year.matches("\\d+")) {
            return false;
        }
        int yearValue = Integer.parseInt(year);
        return yearValue > 0 && yearValue <= 9999;
    }
}