package com.codewithsrv.ptpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProblemSol extends AppCompatActivity {

    Button Problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_problem_sol);

        Problem = (Button) findViewById(R.id.advancesol);
        Problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prob = new Intent(getApplicationContext(),AdvanceSolution.class);
                startActivity(prob);
            }
        });

    }
}