package com.fakestagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.HashMap;

public class HomePage extends AppCompatActivity {
    FirebaseAuth myAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        myAuth = FirebaseAuth.getInstance();
        user = myAuth.getCurrentUser();


    }
}
