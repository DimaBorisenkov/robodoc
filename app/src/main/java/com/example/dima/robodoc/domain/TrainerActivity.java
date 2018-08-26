package com.example.dima.robodoc.domain;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.form.FormContract;
import com.example.dima.robodoc.utils.CreateDiseases;

import org.w3c.dom.Text;

import io.realm.Realm;

public class TrainerActivity extends AppCompatActivity {
    private RadioGroup gender, diseaseGroup;
    private TextView text, bloodText;
    private Button buttonCreate, buttonConfirm;
    private int selectedId, selectedDiseasesNumber, diseasesNumber;
    private boolean selectedGender, checkGender, checkDisease = true;
    private Disease disease;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        setGender();
        setDisease();
        text = findViewById(R.id.textViewSelectGender);
        bloodText = findViewById(R.id.textViewBloodValues);
        buttonCreate = findViewById(R.id.buttonCreate);
        buttonConfirm = findViewById(R.id.buttonDiseaseConfirm);

        final CreateDiseases createDiseases = new CreateDiseases();

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkGender)
                    Toast.makeText(TrainerActivity.this, "Будь ласка, оберіть стать", Toast.LENGTH_SHORT).show();
                else {
                    disease = createDiseases.createDiseases(selectedGender);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Blood temp : disease.getBloodRealmList()) {
                        stringBuilder.append(temp.getName() + ": " + temp.getValue());
                        stringBuilder.append("\n");
                    }

                    bloodText.setText(stringBuilder.toString());
                }
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkDisease)
                    Toast.makeText(TrainerActivity.this, "Будь ласка, оберіть хворобу", Toast.LENGTH_SHORT).show();
                else {
                    if (disease.getNumber() == diseasesNumber) bloodText.setText("peremoga");
                    else bloodText.setText("ne peremoga");
                }
            }
        });


    }


    public void setGender() {
        gender = findViewById(R.id.radioSexGroup);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = gender.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radioButtonMale:
                        text.setText("Обрана стать - чоловік");
                        selectedGender = true;
                        checkGender = true;
                        break;
                    case R.id.radioButtonFemale:
                        text.setText("Обрана стать - жінка");
                        selectedGender = false;
                        checkGender = true;
                        break;
                    default:
                        checkGender = false;
                        break;
                }
            }
        });

    }

    public void setDisease() {
        diseaseGroup = findViewById(R.id.diseasesGroup);
        diseaseGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedDiseasesNumber = diseaseGroup.getCheckedRadioButtonId();
                switch (selectedDiseasesNumber) {
                    case R.id.radioButtonDehydration:
                        diseasesNumber = 0;
                        break;
                    case R.id.radioButtonBloodClotting:
                        diseasesNumber = 1;
                        break;
                    default:
                        checkDisease = false;
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDisease();
    }
}
