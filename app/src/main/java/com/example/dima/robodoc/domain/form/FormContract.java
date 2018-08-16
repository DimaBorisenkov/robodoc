package com.example.dima.robodoc.domain.form;



public interface FormContract {
    interface View {
        void setGender();

        String createName(String hint);


    }

    interface Presenter {

    }
}
