package com.example.dima.robodoc.domain.history;

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
import com.example.dima.robodoc.data.realm.RealmHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class HistoryFragment extends Fragment implements HistoryContract.View{
    private PatientsAdapter patientsAdapter;
    private RecyclerView recyclerView;
    private HistoryContract.Presenter historyPresenter;
    public Realm realm = Realm.getDefaultInstance();
    private RealmHelper realmHelper;
    private RealmChangeListener realmChangeListener;

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

        realmHelper = new RealmHelper(realm);
        realmHelper.retrieveFromDB();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        historyPresenter = new HistoryPresenter();

        patientsAdapter = new PatientsAdapter(realmHelper.refresh(), getContext(), recyclerView);
        recyclerView.setAdapter(patientsAdapter);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                patientsAdapter = new PatientsAdapter(realmHelper.refresh(), getContext(), recyclerView);
                recyclerView.setAdapter(patientsAdapter);
            }
        };

        realm.addChangeListener(realmChangeListener);

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




}
