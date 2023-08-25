package com.codewithsrv.ptpetvet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UserComplaintReg extends AppCompatActivity {

    Context context;
    EditText gt_date,gt_petcat,gt_suffering,gt_issue;
    String str_date,str_petcat,str_suffering,str_issue;
    String URL = "https://drvp.000webhostapp.com/reg_complaint.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint_reg);

        gt_date = findViewById(R.id.gt_date);
        gt_petcat = findViewById(R.id.gt_petcat);
        gt_suffering = findViewById(R.id.gt_suffering);
        gt_issue = findViewById(R.id.gt_issue);

        if (!isConnected()){
            Toast.makeText(getApplicationContext(),"No Internet Connection. Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void RegisterComp(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if (gt_date.getText().toString().equals("")){
            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show();
        }
        else if (gt_petcat.getText().toString().equals("")){
            Toast.makeText(this, "Enter Pet Category", Toast.LENGTH_SHORT).show();
        }
        else if (gt_suffering.getText().toString().equals("")){
            Toast.makeText(this, "Enter Days Suffering", Toast.LENGTH_SHORT).show();
        }
        else if (gt_issue.getText().toString().equals("")){
            Toast.makeText(this, "Enter Problem Description", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            str_date = gt_date.getText().toString().trim();
            str_petcat = gt_petcat.getText().toString().trim();
            str_suffering = gt_suffering.getText().toString().trim();
            str_issue = gt_issue.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    gt_date.setText("");
                    gt_petcat.setText("");
                    gt_suffering.setText("");
                    gt_issue.setText("");
                    Toast.makeText(UserComplaintReg.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UserComplaintReg.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("com_date", str_date);
                    params.put("pet_category", str_petcat);
                    params.put("days_suffering", str_suffering);
                    params.put("desc_prob", str_issue);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(UserComplaintReg.this);
            requestQueue.add(request);

        }

    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    private Boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}