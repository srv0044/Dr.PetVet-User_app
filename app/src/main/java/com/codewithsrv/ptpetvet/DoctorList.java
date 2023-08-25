package com.codewithsrv.ptpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    String URL = "https://drvp.000webhostapp.com/doctor_retrieve.php";
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        listView = findViewById(R.id.myList);
        adapter = new MyAdapter(this, doctorArrayList);
        listView.setAdapter(adapter);

        retrieveData();

    }
    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                doctorArrayList.clear();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("doctor");

                    if (success.equals("1")){

                        for (int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String username = object.getString("username");
                            String email = object.getString("email");
                            String password = object.getString("password");
                            String phone = object.getString("phone");
                            String addess = object.getString("addess");

                            doctor = new Doctor(id,username,email,password,phone,addess);
                            doctorArrayList.add(doctor);
                            adapter.notifyDataSetChanged();


                        }

                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void btn_signup(View view){
        startActivity(new Intent(getApplicationContext(),Signup.class));
    }

}