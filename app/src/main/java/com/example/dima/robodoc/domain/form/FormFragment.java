package com.example.dima.robodoc.domain.form;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.dima.robodoc.domain.history.HistoryFragment;
import com.example.dima.robodoc.domain.result.ResultActivity;
import com.example.dima.robodoc.utils.DiseaseDeterminant;
import com.example.dima.robodoc.utils.NormaDeterminant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;


public class FormFragment extends Fragment implements FormContract.View {

    private RadioGroup gender;
    private TextView text;
    private Button buttonConfirm, buttonClear;
    private ImageView user;
    private boolean genderBoolean;
    private EditText name, hb, rbc, mchc, rtc, plt, esr, wbc, eos, bas, lym, mon;
    private int selectedId;
    private boolean checkGender = false;
    private FormContract.Presenter presenter;
    private Realm realm;
    private Patient patient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);

        setGender();

        presenter = new FormPresenter();
        buttonConfirm = view.findViewById(R.id.buttonConfirm);
        buttonClear = view.findViewById(R.id.buttonClear);
        name = view.findViewById(R.id.name);
        hb = view.findViewById(R.id.hb);
        rbc = view.findViewById(R.id.rbc);
        mchc = view.findViewById(R.id.mchc);
        rtc = view.findViewById(R.id.rtc);
        plt = view.findViewById(R.id.plt);
        esr = view.findViewById(R.id.esr);
        wbc = view.findViewById(R.id.wbc);
        eos = view.findViewById(R.id.eos);
        bas = view.findViewById(R.id.bas);
        lym = view.findViewById(R.id.lym);
        mon = view.findViewById(R.id.mon);

        final EditText[] editTexts = {hb, rbc, mchc, rtc, plt, esr, wbc, eos, bas, lym, mon};

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkName = presenter.checkName(name.getText().toString());

                if (!checkGender) Toast.makeText(getContext(), "Будь ласка, оберіть стать", Toast.LENGTH_SHORT).show();
                if (!checkName) Toast.makeText(getContext(), "Будь ласка, введіть ім'я", Toast.LENGTH_SHORT).show();

                if (checkName && checkGender) {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    patient = new Patient();

                    patient.setDate(simpleDateFormat.format(date).toString());
                    patient.setName(name.getText().toString());
                    patient.setGender(genderBoolean);

                    if (presenter.checkFields(editTexts)) {
                        patient.setDiseases(new RealmList<Disease>());
                        patient.setState(true);
                        patient.setBlood(new RealmList<Blood>());
                        savePatient(patient);
                        transmitValues(patient);
                        setDefault(editTexts);

                    } else {

                        try {
                            Blood blood = new NormaDeterminant().check(presenter.createBloodArrayList(editTexts), genderBoolean);
                            RealmList<Disease> diseases = new DiseaseDeterminant().selectDisease(blood, getContext());
                            patient = presenter.createPatient(patient, blood, diseases);
                            savePatient(patient);
                            transmitValues(patient);
                            setDefault(editTexts);
                        } catch (Exception e){
                            Toast.makeText(getContext(), "Поле не повинно містити лише крапку", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefault(editTexts);
            }
        });
    }


    public void setDefault(EditText... editTexts) {
        user.setImageResource(R.color.back);
        checkGender = false;
        name.setText("");
        text.setText("Будь ласка, оберіть стать");
        for (EditText temp : editTexts) temp.setText("");
    }

    @Override
    public void setGender() {
        View view = getView();
        gender = view.findViewById(R.id.radioSex);
        user = view.findViewById(R.id.user);
        text = view.findViewById(R.id.selectGender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = gender.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radioMale:
                        text.setText("Обрана стать - чоловік");
                        user.setImageResource(R.drawable.man_icon_big);
                        genderBoolean = true;
                        checkGender = true;
                        break;
                    case R.id.radioFemale:
                        text.setText("Обрана стать - жінка");
                        user.setImageResource(R.drawable.woman_icon_big);
                        genderBoolean = false;
                        checkGender = true;
                        break;
                    default:
                        checkGender = false;
                        break;
                }
            }
        });

    }


    @Override
    public void transmitValues(Patient patient) {
        Intent intent = new Intent(getContext(), ResultActivity.class);
        intent.putExtra("type", "form");
        intent.putExtra("id", patient.getId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sortByDate:
                return true;
            case R.id.action_sortByStatus:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_blood_test, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    void savePatient(Patient patient) {
        RealmConfiguration configFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        realm = Realm.getInstance(configFirst);

        Number current = realm.where(Patient.class).max("id");
        long nextId;
        if (current == null) nextId = 1;
        else nextId = current.intValue() + 1;
        patient.setId(nextId);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(patient);
        realm.commitTransaction();
    }

}
