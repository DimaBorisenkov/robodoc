package com.example.dima.robodoc.domain.form;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.result.ResultActivity;
import com.example.dima.robodoc.utils.DiseaseDeterminant;
import com.example.dima.robodoc.utils.NormaDeterminant;

import java.util.ArrayList;


public class FormFragment extends Fragment implements FormContract.View{

    private RadioGroup gender;
    private TextView text;
    private Button button;
    private ImageView user;
    private boolean genderBoolean;
    private EditText hb, rbc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);

        setGender();

        button = view.findViewById(R.id.buttonConfirm);
        hb = view.findViewById(R.id.hb);
        rbc = view.findViewById(R.id.rbc);

        final EditText [] editTexts = {hb, rbc};

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Blood> bloodArrayList = new ArrayList<>();
                Patient patient = new Patient("test");
                patient.setGender(genderBoolean);
                for (EditText temp : editTexts){
                    if(temp.getText().length() > 0){
                        String name = createName(temp.getHint().toString());
                        bloodArrayList.add(new Blood(name.trim(), Double.valueOf(temp.getText().toString())));
                    }
                }

                Blood blood = new NormaDeterminant().check(bloodArrayList, genderBoolean);
                ArrayList<Disease> diseases = new DiseaseDeterminant().selectDisease(blood);

                boolean [] norma = {blood.isHBNorma(), blood.isRBCNorma()};

                patient.setState(true);
                for(boolean temp : norma){
                   if(!temp){
                       patient.setState(false);
                   }
                }
                
                if(!patient.isState()){
                    if(diseases.size() != 0){
                        patient.setDiseases(diseases);
                    }
                }

                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra("type", "form");
                intent.putExtra("patient", patient);
                startActivity(intent);





            }
        });
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
                            genderBoolean = true;
                            break;
                        case R.id.radioFemale:
                            text.setText("Обрана стать - жінка");
                            user.setImageResource(R.drawable.woman_icon_big);
                            genderBoolean = false;
                            break;
                    }
                }
            }
        });

    }

    @Override
    public String createName(String hint) {

        String name = "";
        char [] nameSymbols = hint.toCharArray();
        for(int i = 0; i < 4 ; i++){
            name += nameSymbols[i];

        }
        return name;
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
