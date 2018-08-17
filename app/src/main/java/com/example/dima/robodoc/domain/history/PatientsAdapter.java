package com.example.dima.robodoc.domain.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.result.ResultActivity;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Patient> patients;
    private Context context;
    private RecyclerView recyclerView;


    public PatientsAdapter(Context context, List<Patient> patients) {
        this.context = context;
        this.patients = patients;
    }

    public PatientsAdapter(List<Patient> patients, Context context, RecyclerView recyclerView) {
        this.inflater = LayoutInflater.from(context);
        this.patients = patients;
        this.context = context;
        this.recyclerView = recyclerView;
    }


    @Override
    public PatientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.patient_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final PatientsAdapter.ViewHolder holder, int position) {
        final Patient patient = patients.get(position);
        StringBuilder diseases = new StringBuilder();
        String check = "";
        if(patient.getDiseases() == null){
             check = "Хвороб немає";
        } else {
            diseases.append("Хвороби: ");
            for (Disease temp : patient.getDiseases()) {
                diseases.append(temp.getName().toLowerCase());
                diseases.append(", ");
            }
            if (diseases.length() > 35) {
                check = diseases.substring(0, 35) + "...";
            } else {
                char[] message = diseases.toString().toCharArray();
                for (int i = 0; i < message.length - 2; i++) {
                    check += message[i];
                }
            }
        }



        /*if(check.length() > 35){
            check = check.substring(0, 35) + "...";
        }*/
        /*if (diseases.length() > 35) {
            check = diseases.substring(0, 35) + "...";
        } else {
            check = diseases;
        }*/

        holder.diseasesText.setText(check);
        holder.nameText.setText(patient.getName());
        holder.dateText.setText(patient.getDate());
        holder.stateImage.setImageResource(patient.getImageStatus());
        holder.genderImage.setImageResource(patient.getImageGender());
        holder.rowLinearLayout.setBackgroundColor(patient.getStatusColor());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("patient", patient);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return patients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;

        final TextView nameText, diseasesText, dateText;
        final ImageView genderImage, stateImage;
        LinearLayout rowLinearLayout;

        ViewHolder(View view) {
            super(view);
            layout = view;
            nameText = view.findViewById(R.id.name);
            diseasesText = view.findViewById(R.id.diseases);
            dateText = view.findViewById(R.id.date);
            rowLinearLayout = view.findViewById(R.id.linear_layout);
            genderImage = view.findViewById(R.id.gender);
            stateImage = view.findViewById(R.id.state);

        }

    }
}
