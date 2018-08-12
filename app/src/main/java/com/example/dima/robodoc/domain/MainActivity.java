package com.example.dima.robodoc.domain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dima.robodoc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnBloodTest, btnTrainer, btnSimpleTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBloodTest = findViewById(R.id.buttonBloodTestActivity);
        btnTrainer = findViewById(R.id.buttonTrainerActivity);
        btnSimpleTest = findViewById(R.id.buttonSimpleTestActivity);
        btnBloodTest.setOnClickListener(this);
        btnTrainer.setOnClickListener(this);
        btnSimpleTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.buttonBloodTestActivity:
                intent = new Intent(MainActivity.this, BloodTestActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonTrainerActivity:
                intent = new Intent(MainActivity.this, TrainerActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonSimpleTestActivity:
                intent = new Intent(MainActivity.this, SimpleTestActivity.class);
                startActivity(intent);
                break;
        }
    }


}
