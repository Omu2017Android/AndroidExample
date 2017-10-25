package com.example2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] kitaplar
            = {"Suç ve Ceza","Fareler ve İnsanlar","Sefiller","Satranç","İnsan Neyle Yaşar","Beyaz Diş","Yeraltından Notlar","Bilinmeyen Bir Kadının Mektubu","Genç Werther'in Acıları"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView liste = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, kitaplar);

        liste.setAdapter(adapter);
    }
}
