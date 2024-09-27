package com.dungnt.ex05;

import android.annotation.SuppressLint;
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

import java.text.DecimalFormat;

public class SolveEquation extends AppCompatActivity {

    Button btnContinue, btnSolve, btnFinish;
    EditText edt_aValue, edt_bValue, edt_cValue;
    TextView tv_resultSolveEquation;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solve_equation);

        tv_resultSolveEquation = findViewById(R.id.tv_resultSolveEquation);

        btnContinue = findViewById(R.id.btn_continue);
        btnSolve = findViewById(R.id.btn_solve);
        btnFinish = findViewById(R.id.btn_finish);

        edt_aValue = findViewById(R.id.et_aValue);
        edt_bValue = findViewById(R.id.et_bValue);
        edt_cValue = findViewById(R.id.et_cValue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_aValue.setText("");
                edt_bValue.setText("");
                edt_cValue.setText("");
                edt_aValue.requestFocus();
            }
        });

        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sa = edt_aValue.getText()+"";
                String sb = edt_bValue.getText()+"";
                String sc = edt_cValue.getText()+"";
                int a=Integer.parseInt(sa);
                int b=Integer.parseInt(sb);
                int c=Integer.parseInt(sc);
                String kq="";
                DecimalFormat dcf=new DecimalFormat("0.00");
                if(a==0)
                {
                    if(b==0)
                    {
                        if(c==0)
                            kq="PT vô số nghiệm";
                        else
                            kq="PT vô nghiệm";
                    }
                    else
                    {
                        kq="Pt có 1 No, x="+dcf.format(-c/b);
                    }
                }
                else
                {
                    double delta=b*b-4*a*c;
                    if(delta<0)
                    {
                        kq="PT vô nghiệm";
                    }
                    else if(delta==0)
                    {
                        kq="Pt có No kép x1=x2="+dcf.format(-b/(2*a));
                    }
                    else
                    {
                        kq="Pt có 2 No: x1="+dcf.format((-b+Math.sqrt(delta))/(2*a))+"; x2="+dcf.format((-
                                b-Math.sqrt(delta))/(2*a));
                    }
                }
                tv_resultSolveEquation.setText(kq);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}