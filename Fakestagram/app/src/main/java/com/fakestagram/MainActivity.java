package com.fakestagram;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth myAuth;
    private EditText email;
    private EditText password;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextMail);
        password = (EditText) findViewById(R.id.editTextPassword);

        Button login = (Button) findViewById(R.id.buttonLogin);
        Button register = (Button) findViewById(R.id.buttonRegister);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

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
    public void register(String email,String password){
        myAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //başarı durumu
                    Toast.makeText(MainActivity.this, "Kayıt olundu!", Toast.LENGTH_SHORT).show();
                }else{
                    //hata durumu
                    Toast.makeText(MainActivity.this, "Başarısız!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void login(String email,String password){
        myAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user=myAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this,
                            "Giriş başarılı", Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(this,HomePageActivity.class);
                    Toast.makeText(MainActivity.this, "Email: "
                            +user.getEmail().toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);

                    }else
                    Toast.makeText(
                            MainActivity.this, "Giriş yapılamadı!"
                            , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonLogin){
            if(validate())
                login(this.email.getText().toString(),this.password.getText().toString());
        }else if(id == R.id.buttonRegister){
            if(validate())
                register(this.email.getText().toString(),this.password.getText().toString());
        }
    }
}
