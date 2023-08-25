package com.codewithsrv.ptpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvanceSolution extends AppCompatActivity {
    Button p1,p2,p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_solution);

        p1 = (Button) findViewById(R.id.pay1);
        p2 = (Button) findViewById(R.id.pay2);
        p3 = (Button) findViewById(R.id.pay3);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay1 = new Intent(getApplicationContext(),UserMakePayment.class);
                startActivity(pay1);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay2 = new Intent(getApplicationContext(),UserMakePayment.class);
                startActivity(pay2);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay3 = new Intent(getApplicationContext(),UserMakePayment.class);
                startActivity(pay3);
            }
        });

    }
}
