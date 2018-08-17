package com.example.dima.robodoc.domain.form;


public class FormPresenter implements FormContract.Presenter{

    @Override
    public boolean checkName(String name) {
        boolean check = true;
        if (name.trim().length() == 0) {
            check = false;
        }
        return check;

    }

    @Override
    public String createName(String hint) {

        String name = "";
        char[] nameSymbols = hint.toCharArray();
        for (int i = 0; i < 4; i++) {
            name += nameSymbols[i];

        }
        return name;
    }
}
