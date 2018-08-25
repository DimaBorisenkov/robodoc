package com.example.dima.robodoc.domain.result;

import android.annotation.SuppressLint;
import android.app.Person;
import android.content.Intent;
import android.content.res.Resources;
import android.database.CursorJoiner;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.EditActivity;
import com.example.dima.robodoc.domain.archive.AddPatientActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {
    private TextView patientName, patientState, patientDiseases, patientDate, patientBlood;
    private ImageView imageView;
    private long id;
    private Patient patient;
    private String type;
    private StringBuilder diseases, blood;
    private ResultPresenter presenter;
    private Resources resources;
    private Drawable[] drawables;
    private Button buttonDelete, buttonEdit, buttonTransmit;
    private LinearLayout layout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getValues();

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonTransmit = findViewById(R.id.buttonTransmit);
        if (type.equals("history")) {
            buttonDelete.setVisibility(View.VISIBLE);
            buttonEdit.setVisibility(View.VISIBLE);
            buttonTransmit.setVisibility(View.VISIBLE);
        }

        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        patientDate = findViewById(R.id.textViewPatientDate);
        patientBlood = findViewById(R.id.textViewPatientBlood);
        imageView = findViewById(R.id.imageView);
        layout = findViewById(R.id.nameLayout);

        RealmConfiguration configFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        final Realm realm = Realm.getInstance(configFirst);
        try {
            patient = realm.where(Patient.class).equalTo("id", id).findFirst();
        } finally {
            realm.close();
        }

        resources = getResources();
        imageView.setImageDrawable(resources.getDrawable(R.layout.layer, null));
        presenter = new ResultPresenter();
        presenter.setView(this);


        setValues();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                patient.deleteFromRealm();
                realm.commitTransaction();
                finish();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ResultActivity.this, EditActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

        buttonTransmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, AddPatientActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "result");
                startActivity(intent);
            }
        });

    }

    @Override
    public void getValues() {
        type = getIntent().getStringExtra("type");
        id = getIntent().getLongExtra("id", 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setValues() {

        diseases = presenter.createDiseases(patient);
        blood = presenter.createBlood(patient);

        patientName.setText(patient.getName());
        patientDate.setText(patient.getDate());
        patientDiseases.setText(diseases);
        patientBlood.setText(blood);

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
            if (patient.isGender()) {
                imageView.setImageResource(R.drawable.man_body);
            } else {
                imageView.setImageResource(R.drawable.woman_body);
            }
        }

        if (patient.isState()) {
            patientState.setText("Здоровий");
            layout.setBackgroundColor(Color.parseColor("#6664DD17"));
        } else {
            patientState.setText("Хворий");
            layout.setBackgroundColor(Color.parseColor("#9DEF0407"));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        setValues();
    }
}
