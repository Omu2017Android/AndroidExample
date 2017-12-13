package firebase.firebaseauth_realtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.SlideInEffect;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    JazzyListView list;
    List<User> personList = new ArrayList<User>();
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        list = (JazzyListView ) findViewById(R.id.listView);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personList.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    personList.add(user);
                }
                customAdapter =  new CustomAdapter(personList,UserListActivity.this);
                list.setAdapter(customAdapter);
                list.setTransitionEffect(new SlideInEffect());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserListActivity.this, "Hata!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
