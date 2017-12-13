package firebase.firebaseauth_realtime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email;
    EditText password;
    EditText userName;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");
        auth = FirebaseAuth.getInstance();

        email = ( EditText ) findViewById( R.id.editTextEmail );
        password = ( EditText ) findViewById( R.id.editTextPassword );
        userName = (EditText) findViewById( R.id.editTextName);
        Button buttonSignIn = ( Button ) findViewById( R.id.buttonSignIn );
        Button buttonSignUp = ( Button ) findViewById( R.id.buttonSignUp );

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                    SignIn(email.getText().toString(),password.getText().toString());
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                SignUp(email.getText().toString(),password.getText().toString());

            }
        });
    }

    public void SignIn(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( MainActivity.this, homePage.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this, "Giriş başarısız bilgilerinizi kontrol edin!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void SignUp(String email, String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Kayıt olundu!", Toast.LENGTH_SHORT).show();
                    reference = reference.child(auth.getCurrentUser().getUid());
                    reference.child("name").setValue(userName.getText().toString());
                    reference.child("uid").setValue(auth.getCurrentUser().getUid());
                }else{
                    Toast.makeText(MainActivity.this, "Kayıt başarısız bilgilerinizi kontrol edin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean validate(){
        boolean valid = true;

        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if(email.isEmpty()  || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
            this.email.setError("Geçerli bir mail adresi girin!");
        }
        else if(password.isEmpty() || password.length() < 5 || password.length() > 32 ){
            valid = false;
            this.password.setError("Parola boş bırakılamaz! Parolanız 6-32 karakter uzunluğunda olmalıdır!");
        }

        return valid;
    }
}
