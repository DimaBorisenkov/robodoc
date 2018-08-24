package com.example.dima.robodoc.domain.archive;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.history.PatientsAdapter;
import com.example.dima.robodoc.domain.result.ResultActivity;

import java.util.List;


public class PatientBaseAdapter extends RecyclerView.Adapter<PatientBaseAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Patient> patients;
    private Context context;
    private RecyclerView recyclerView;

    public PatientBaseAdapter(List<Patient> patients, Context context, RecyclerView recyclerView) {
        this.inflater = LayoutInflater.from(context);
        this.patients = patients;
        this.context = context;
        this.recyclerView = recyclerView;
    }


    @Override
    public PatientBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.base_patient_row, parent, false);
        return new PatientBaseAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final PatientBaseAdapter.ViewHolder holder, int position) {
        final Patient patient = patients.get(position);

        holder.nameText.setText(createName(patient.getName()));
        holder.addressText.setText(patient.getAddress());
        holder.ageText.setText(String.valueOf(patient.getAge()));
        holder.historyText.setText(createHistory(patient.getHistory()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, patient.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String createHistory(String history) {
        StringBuilder stringBuilder = new StringBuilder();
        if(history.length() > 15){
            char [] symbols = history.toCharArray();
            for(int i = 0; i < 15; i++){
                stringBuilder.append(symbols[i]);
            }
        }
        return stringBuilder.toString();
    }

    private String createName(String name) {
        if (name.length() > 10) {
            char[] symbols = name.toCharArray();
            String text = "";
            for (int i = 0; i < 10; i++) {
                text += symbols[i];
            }
            text += "...";
            return text;
        } else return name;
    }


    @Override
    public int getItemCount() {
        return patients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        final TextView nameText, ageText, addressText, historyText;

        ViewHolder(View view) {
            super(view);
            layout = view;
            nameText = view.findViewById(R.id.name);
            ageText = view.findViewById(R.id.age);
            addressText = view.findViewById(R.id.address);
            historyText = view.findViewById(R.id.history);

        }

    }
}