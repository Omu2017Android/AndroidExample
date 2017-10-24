package com.example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText lastName;
    EditText eMail;
    EditText password;
    EditText repeatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextName);
        lastName = (EditText) findViewById(R.id.editTextLastName);
        eMail = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        repeatedPassword = (EditText) findViewById(R.id.editTextRepatedPassword);

        Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arePasswordsEqual(password,repeatedPassword) && areAnyTextEmpty(name,lastName,eMail)){
                    Intent intent = new Intent(MainActivity.this,HomePageActivity.class);
                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("lastName",lastName.getText().toString());
                    intent.putExtra("eMail",eMail.getText().toString());

                    startActivity(intent);
                }


            }
        });

    }

    private boolean arePasswordsEqual(EditText password , EditText repeatedPassword) {

        if (password.getText().toString().equals(repeatedPassword.getText().toString())) {
            if(password.getText().toString().length() < 5){
                Toast.makeText(MainActivity.this,"Şifreniz en az 6 karakter olmalı!",Toast.LENGTH_LONG).show();
                return false;
            }else{
                return true;
            }
        }else{
            Toast.makeText(MainActivity.this,"Şifreler uyuşmuyor!",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    private boolean areAnyTextEmpty(EditText name,EditText lastName, EditText eMail ){
        if(name.getText().toString().isEmpty() && lastName.getText().toString().isEmpty() && eMail.getText().toString().isEmpty()){
            Toast.makeText(this, "Bütün alanları doldurduğundan emin ol!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

}
