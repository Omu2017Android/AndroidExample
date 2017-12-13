package firebase.firebaseauth_realtime;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class homePage extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseStorage storage;
    ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 123;
    Uri filePath;

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                Picasso.with(homePage.this).load(filePath).fit().centerCrop().into(imageView);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        user = auth.getCurrentUser();

        imageView = ( ImageView ) findViewById( R.id.imageView );
        Button buttonSave = ( Button ) findViewById( R.id.buttonSave );
        Button buttonLogOut = ( Button ) findViewById( R.id.buttonLogout );
        Button buttonChoose = ( Button ) findViewById( R.id.buttonChoose );
        Button buttonListPage = ( Button ) findViewById( R.id.buttonListPage );

        buttonListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,UserListActivity.class);
                startActivity(intent);
            }
        });
        StorageReference reference = storage.getReference().child("users").child(user.getUid());
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(homePage.this).load(uri).fit().centerCrop().into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(homePage.this, "Beklenmedik bir hata!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Resim Seç!"),PICK_IMAGE_REQUEST);
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filePath != null){
                    StorageReference ref = storage.getReference().child("users").child(user.getUid());
                    ref.putFile(filePath).addOnSuccessListener
                            (new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(homePage.this, "Fotoğraf kaydedildi!", Toast.LENGTH_SHORT).show();
                            imageView.setImageBitmap(null);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(homePage.this, "Yüklenemedi!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


}
