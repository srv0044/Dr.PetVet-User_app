package com.codewithsrv.ptpetvet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class UserDashboard extends AppCompatActivity {

    CardView c1,c2,c3,c4;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        changeStatusBarColor();

        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        c3 = (CardView) findViewById(R.id.card3);
        c4 = (CardView) findViewById(R.id.card4);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerComplaint = new Intent(getApplicationContext(),UserComplaintReg.class);
                startActivity(registerComplaint);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent probSol = new Intent(getApplicationContext(),UserProblemSol.class);
                startActivity(probSol);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent docList = new Intent(getApplicationContext(),DoctorList.class);
                startActivity(docList);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petProf = new Intent(getApplicationContext(),PetProfile.class);
                startActivity(petProf);
            }
        });

        logout = findViewById(R.id.UserLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = getSharedPreferences("demo", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent intent = new Intent(UserDashboard.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Toast.makeText(UserDashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("alert!")
                .setMessage("Do you Really Want to Exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int whichButton) {
                        UserDashboard.super.onBackPressed();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}