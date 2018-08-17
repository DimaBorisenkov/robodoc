package com.example.dima.robodoc.domain.history;

import com.example.dima.robodoc.R;
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

            diseases.add(new Disease("Зневоднення", R.drawable.dehydration));

            anotherDiseases.add(new Disease("Зневоднення", R.drawable.dehydration));
            anotherDiseases.add(new Disease("Згущення крові", R.drawable.blood_clotting));

            patients.add(new Patient("Anna", date, false, false, diseases));
            patients.add(new Patient("Dima", date, true, true, new ArrayList<Disease>()));
            patients.add(new Patient("Oleg", date, true, false, anotherDiseases));

            view.setHistory(patients);
        }
    }

