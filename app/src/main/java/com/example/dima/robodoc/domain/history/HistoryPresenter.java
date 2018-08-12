package com.example.dima.robodoc.domain.history;

import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter{
        HistoryContract.View view;
        public List<Patient> patients;

        @Override
        public void setView(HistoryContract.View view) {
            this.view = view;
            patients = new ArrayList<>();
            Date localDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            String date = simpleDateFormat.format(localDate).toString();

            ArrayList<Disease> diseases = new ArrayList<>();
            ArrayList<Disease> anotherDiseases = new ArrayList<>();

            diseases.add(new Disease("Анемія"));
            diseases.add(new Disease("Кров"));
            diseases.add(new Disease("Голова"));

            anotherDiseases.add(new Disease("Анемія"));
            anotherDiseases.add(new Disease("Печінка"));
            anotherDiseases.add(new Disease("Крововтрата"));
            anotherDiseases.add(new Disease("Недостаток вітамінів"));

            patients.add(new Patient("Anna", date, false, true, diseases));
            patients.add(new Patient("Dima", date, true, false, anotherDiseases));
            patients.add(new Patient("Oleg", date, true, true, diseases));

            view.setHistory(patients);
        }
    }

