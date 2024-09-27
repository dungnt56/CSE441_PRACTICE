package com.dungnt.ex06;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSubmitInformation;
    EditText etFullname, etIdCard;
    EditText etAddtional;
    RadioGroup certificate;
    CheckBox readNewspaper, readBook, coding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnSubmitInformation = findViewById(R.id.btn_submitInformation);
        etFullname = findViewById(R.id.et_fullname);
        etIdCard = findViewById(R.id.et_idCard);
        etAddtional = findViewById(R.id.et_additional);
        readNewspaper = findViewById(R.id.cb_preferNewspaper);
        readBook = findViewById(R.id.cb_preferBook);
        coding = findViewById(R.id.cb_preferCoding);
        certificate = findViewById(R.id.radioGroup);

        btnSubmitInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInformation();
            }
        });

    }

    public void submitInformation(){

        String fullname = etFullname.getText().toString();
        fullname = fullname.trim();
        if (TextUtils.isEmpty(fullname)){
            etFullname.requestFocus();
            etFullname.selectAll();
            etFullname.setError("Họ Tên là trường bắt buộc");
            return;
        }
        if (fullname.length() < 3){
            etFullname.requestFocus();
            etFullname.selectAll();
            etFullname.setError("Họ Tên phải >= 3 ký tự");
            return;
        }

        String idCard = etIdCard.getText().toString();
        idCard = idCard.trim();
        if (idCard.length() != 9){
            etIdCard.requestFocus();
            etIdCard.selectAll();
            etIdCard.setError("CMND phải đúng 9 ký tự");
            return;
        }

        int selectedId = certificate.getCheckedRadioButtonId();
        String certificateChoose = "";

        // Get value certificate
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedOption = selectedRadioButton.getText().toString();
            certificateChoose = selectedOption + "";
        } else {
            Toast.makeText(this, "Phải chọn bằng cấp",Toast.LENGTH_LONG).show();
            return;
        }

        // Get interest
        String interest="";
        if(readNewspaper.isChecked())
            interest+=readNewspaper.getText()+"\n";
        if(readBook.isChecked())
            interest+=readBook.getText()+"\n";
        if(coding.isChecked())
            interest+=coding.getText()+"\n";

        String additional = etAddtional.getText()+"";

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        String msg=fullname+"\n";
        msg+= idCard+"\n";
        msg+=certificateChoose+"\n";
        msg+=interest;
        msg+="—————————–\n";
        msg+="Thông tin bổ sung:\n";
        msg+=additional+ "\n";
        msg+="—————————–";
        builder.setMessage(msg);
        builder.create().show();
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        AlertDialog.Builder b =new
                AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.inform);
        b.setPositiveButton("Yes", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }});
        b.setNegativeButton("No", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
        b.create().show();
    }
}