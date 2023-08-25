package com.codewithsrv.ptpetvet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Doctor> {

    Context context;
    List<Doctor> arrayListDoctor;

    public MyAdapter(@NonNull Context context, List<Doctor> arrayListDoctor) {
        super(context, R.layout.custom_list_item,arrayListDoctor);

        this.context = context;
        this.arrayListDoctor = arrayListDoctor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListDoctor.get(position).getId());
        tvName.setText(arrayListDoctor.get(position).getUsername());

        return view;
    }
}
