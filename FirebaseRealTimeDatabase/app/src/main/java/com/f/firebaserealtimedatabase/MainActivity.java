package com.f.firebaserealtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.f.firebaserealtimedatabase.models.Tweet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText editTextMessage;
    EditText editTextuserName;
    List<Tweet> tweetList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("tweets");

        editTextMessage = ( EditText ) findViewById( R.id.editTextMessage );
        editTextuserName = ( EditText ) findViewById( R.id.editTextUserName );

        Button buttonSend = ( Button ) findViewById( R.id.buttonSend );

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = reference.push().getKey();
                String message = editTextMessage.getText().toString();
                String userName= editTextuserName.getText().toString();

                Tweet tweet  = new Tweet(key,message,ServerValue.TIMESTAMP,userName);

                reference.child(tweet.getKey()).setValue(tweet);
            }
        });



        tweetList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tweetList.clear();
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    Log.d("FİREBASE",snap.child("name").getValue(String.class));

                    Tweet tweet = new Tweet(snap.child("message").getValue().toString(),
                            snap.child("name").getValue().toString());

                    tweetList.add(tweet);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ListView listViewTweets = (ListView ) findViewById(R.id.listViewTweet );

        CustomAdapter adapter = new CustomAdapter(this,tweetList);

        listViewTweets.setAdapter(adapter);

    }

}
