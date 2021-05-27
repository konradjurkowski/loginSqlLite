package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button btnSignUp;
    TextView isRegistered;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        repassword = findViewById(R.id.repassword);
        btnSignUp = findViewById(R.id.btnSignIn);
        isRegistered = findViewById(R.id.isRegistered);

        myDB = new DBHelper(this);

        isRegistered.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)) {
                        Boolean userCheckResult = myDB.checkUsername(user);
                        if(!userCheckResult) {
                            Boolean regResult = myDB.insertData(user, pass);
                            if(regResult) {
                                Toast.makeText(MainActivity.this, "Rejestracja przebiegła pomyślnie!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Rejestracja nieudana!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Uzytkownik o takim nicku już istnieje!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Hasła nie są takie same!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}