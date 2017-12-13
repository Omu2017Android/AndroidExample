package com.f.firebaserealtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.f.firebaserealtimedatabase.models.Tweet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Tweet> tweetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("tweets");


    }
}
