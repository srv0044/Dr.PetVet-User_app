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

public class PetProfile extends AppCompatActivity {

    Context context;
    EditText pet_name,pet_type,pet_breed,pet_gender,pet_dob;
    String str_name,str_type,str_breed,str_gender,str_dob;
    String URL = "https://drvp.000webhostapp.com/pet_profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        pet_name = findViewById(R.id.petName);
        pet_type = findViewById(R.id.petType);
        pet_breed = findViewById(R.id.petBreed);
        pet_gender = findViewById(R.id.petgender);
        pet_dob = findViewById(R.id.petDob);

        if (!isConnected()){
            Toast.makeText(getApplicationContext(),"No Internet Connection. Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void Save(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if (pet_name.getText().toString().equals("")){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }
        else if (pet_type.getText().toString().equals("")){
            Toast.makeText(this, "Enter Pet Type", Toast.LENGTH_SHORT).show();
        }
        else if (pet_breed.getText().toString().equals("")){
            Toast.makeText(this, "Enter Pet Breed", Toast.LENGTH_SHORT).show();
        }
        else if (pet_gender.getText().toString().equals("")){
            Toast.makeText(this, "Enter Pet Gender", Toast.LENGTH_SHORT).show();
        }
        else if (pet_dob.getText().toString().equals("")){
            Toast.makeText(this, "Enter Pet DOB", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            str_name = pet_name.getText().toString().trim();
            str_type = pet_type.getText().toString().trim();
            str_breed = pet_breed.getText().toString().trim();
            str_gender = pet_gender.getText().toString().trim();
            str_dob = pet_dob.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    pet_name.setText("");
                    pet_type.setText("");
                    pet_breed.setText("");
                    pet_gender.setText("");
                    pet_dob.setText("");
                    Toast.makeText(PetProfile.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PetProfile.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("pet_name", str_name);
                    params.put("pet_type", str_type);
                    params.put("pet_breed", str_breed);
                    params.put("pet_gender", str_gender);
                    params.put("pet_dob", str_dob);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(PetProfile.this);
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