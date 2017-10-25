package com.example3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final List<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users.add(new User("Furkan Kayalı",true));
        users.add(new User("Erdem Şahin",true));
        users.add(new User("Recai Oktaş",false));
        users.add(new User("İsmail İşeri",false));

        CustomAdapter adapter = new CustomAdapter(this,users);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }
}
