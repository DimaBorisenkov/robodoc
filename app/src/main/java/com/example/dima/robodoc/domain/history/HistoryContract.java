package com.example.dima.robodoc.domain.history;


public interface HistoryContract {
    interface View {
        void refresh();

    }

    interface Presenter {
        void setView(HistoryContract.View view);


    }
}
