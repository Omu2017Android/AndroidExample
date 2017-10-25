package com.example1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView fullName = (TextView) findViewById(R.id.textViewFullName);
        TextView eMail = (TextView) findViewById(R.id.textViewEmail);

        fullName.setText(getIntent().getStringExtra("name")+"\t"+getIntent().getStringExtra("lastName"));
        eMail.setText(getIntent().getStringExtra("eMail"));
    }
}
