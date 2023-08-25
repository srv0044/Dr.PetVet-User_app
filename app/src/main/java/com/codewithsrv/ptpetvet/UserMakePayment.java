package com.codewithsrv.ptpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserMakePayment extends AppCompatActivity {

    Button Payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_make_payment);

        Payment = (Button) findViewById(R.id.Paybtn);
        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payment = new Intent(getApplicationContext(),UserDashboard.class);
                startActivity(payment);
                Toast.makeText(UserMakePayment.this, "Your Payment is Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}