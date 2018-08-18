package com.example.dima.robodoc.domain.history;


public class HistoryPresenter implements HistoryContract.Presenter {
    HistoryContract.View view;

    @Override
    public void setView(HistoryContract.View view) {
        this.view = view;

    }


}

