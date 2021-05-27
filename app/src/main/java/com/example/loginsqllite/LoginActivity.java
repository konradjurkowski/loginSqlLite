package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnSignUp;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        btnSignUp = findViewById(R.id.btnSignIn);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Proszę wypełnij pola!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = myDB.checkUsernamePassword(user, pass);
                    if(result) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Nieprawidłowe dane logowania!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}