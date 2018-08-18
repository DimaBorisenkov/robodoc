package com.example.dima.robodoc.domain.result;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {
    private TextView patientName, patientState, patientDiseases, patientDate;
    private ImageView imageView;
    private Patient patient;
    private String type;
    private StringBuilder diseases;
    private ResultPresenter presenter;
    private Resources resources;
    private Drawable[] drawables;
    private Button buttonDelete;
    private RelativeLayout layout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonDelete = findViewById(R.id.buttonDelete);
        getValues();
        if(type.equals("history")) buttonDelete.setVisibility(View.VISIBLE);

        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        patientDate = findViewById(R.id.textViewPatientDate);
        imageView = findViewById(R.id.imageView);
        layout = findViewById(R.id.nameLayout);
        resources = getResources();
        imageView.setImageDrawable(resources.getDrawable(R.layout.layer, null));
        presenter = new ResultPresenter();
        presenter.setView(this);
        diseases = presenter.createDiseases(patient);

        setValues();

    }

    @Override
    public void getValues() {
        type = getIntent().getStringExtra("type");
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setValues() {
        if (patient.getDiseases() != null) {
            drawables = new Drawable[patient.getDiseases().size() + 1];
            if (patient.isGender()) {
                drawables[0] = resources.getDrawable(R.drawable.man_body, null);
            } else {
                drawables[0] = resources.getDrawable(R.drawable.woman_body, null);
            }
            for (int i = 1; i < drawables.length; i++) {
                drawables[i] = resources.getDrawable(
                        patient.getDiseases().get(i - 1).getImageId(), null);
            }
            LayerDrawable layerDrawable = new LayerDrawable(drawables);
            imageView.setImageDrawable(layerDrawable);

        } else {
            if(patient.isGender()){
                imageView.setImageResource(R.drawable.man_body);
            } else {
                imageView.setImageResource(R.drawable.woman_body);
            }
        }

        patientName.setText(patient.getName());
        patientDate.setText(patient.getDate());
        int color;
        if (patient.isState()) {
            color = Color.parseColor("#6664DD17");
            patientState.setText("Здоровий");
            layout.setBackgroundColor(color);
        } else {
            color = Color.parseColor("#9DEF0407");
            patientState.setText("Хворий");
            layout.setBackgroundColor(color);
        }

        patientDiseases.setText(diseases);

    }
}
