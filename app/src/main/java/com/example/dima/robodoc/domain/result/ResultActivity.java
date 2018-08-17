package com.example.dima.robodoc.domain.result;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {
    private TextView patientName, patientState, patientDiseases;
    private ImageView imageView;
    private Patient patient;
    private String type;
    private StringBuilder diseases;
    private ResultPresenter presenter;
    private Resources resources;
    private Drawable[] drawables;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getValues();
        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        imageView = findViewById(R.id.imageView);

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
        drawables = new Drawable[patient.getDiseases().size() + 1];

        if (patient.isGender()) {
            drawables[0] = resources.getDrawable(R.drawable.man_body, null);
        } else {
            drawables[0] = resources.getDrawable(R.drawable.woman_body, null);
        }

        for(int i = 1; i < drawables.length; i++){
            drawables[i] = resources.getDrawable(
                    patient.getDiseases().get(i - 1).getImageId(), null);
        }

        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        imageView.setImageDrawable(layerDrawable);

        patientName.setText(patient.getName());

        if (patient.isState()) {
            patientState.setText("Здоровий");
            patientState.setBackgroundColor(GREEN);
            patientName.setBackgroundColor(GREEN);
        } else {
            patientState.setText("Хворий");
            patientName.setBackgroundColor(RED);
            patientState.setBackgroundColor(RED);
        }

        patientDiseases.setText(diseases);

    }
}
