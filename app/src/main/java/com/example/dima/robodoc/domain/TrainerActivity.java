package com.example.dima.robodoc.domain;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dima.robodoc.R;

import org.w3c.dom.Text;

public class TrainerActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        textView = findViewById(R.id.textView2);
        long id = getIntent().getLongExtra("id", 0);

        textView.setText("" + id);

    }


}
