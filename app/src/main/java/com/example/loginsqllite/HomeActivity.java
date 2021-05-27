package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userinfo = findViewById(R.id.userInfo);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        userinfo.setText(username);
    }
}