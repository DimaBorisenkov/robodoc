package com.example.dima.robodoc.domain.result;


import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

public class ResultPresenter implements ResultContract.Presenter {
    ResultContract.View view;

    @Override
    public void setView(ResultContract.View view) {
        this.view = view;
    }

    @Override
    public StringBuilder createDiseases(Patient patient) {
        StringBuilder diseases = new StringBuilder();
        if (patient.getDiseases() != null && patient.getDiseases().size() > 0) {
            diseases.append("Є підозри на такі хвороби:");
            diseases.append("\n");
            for (Disease temp : patient.getDiseases()) {
                diseases.append("• " + temp.getName());
                diseases.append("\n");
            }
        } else {
            if(patient.isState()) diseases.append("Хвороб немає");
            else diseases.append("Один з показників крові не у нормі");

        }
       return diseases;

    }

}
