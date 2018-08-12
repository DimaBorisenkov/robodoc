package com.example.dima.robodoc.domain.form;

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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;


public class FormFragment extends Fragment implements FormContract.View{

    private RadioGroup gender;
    private TextView text;
    private Button button;
    private ImageView user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);

        setGender();

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
                int selectedId = gender.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(getContext(), "Будь ласка, оберіть стать", Toast.LENGTH_SHORT).show();
                } else {
                    switch (selectedId) {
                        case R.id.radioMale:
                            text.setText("Обрана стать - чоловік");
                            user.setImageResource(R.drawable.man_icon_big);
                            break;
                        case R.id.radioFemale:
                            text.setText("Обрана стать - жінка");
                            user.setImageResource(R.drawable.woman_icon_big);
                            break;
                    }
                }
            }
        });

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
