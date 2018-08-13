package com.example.dima.robodoc.domain.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.ResultActivity;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContract.View{
    private PatientsAdapter patientsAdapter;
    private RecyclerView recyclerView;
    private HistoryContract.Presenter historyPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();


        historyPresenter = new HistoryPresenter();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        patientsAdapter = new PatientsAdapter(getContext(), new HistoryPresenter().patients);
        recyclerView.setAdapter(patientsAdapter);

        historyPresenter.setView(this);
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

    @Override
    public void setHistory(List<Patient> patients) {
        patientsAdapter = new PatientsAdapter(patients, getContext(), recyclerView);
        recyclerView.setAdapter(patientsAdapter);

    }



}
