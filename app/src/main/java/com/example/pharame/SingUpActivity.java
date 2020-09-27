package com.example.pharame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Data.MyAppDataBase;
import Data.MyDao;
import model.User;

public class SingUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button callSingIn;
    EditText PasswordR;
    EditText Passwordconf;
    Button Bsingup;

    private MyDao myDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        myDao = MyAppDataBase.creatmydbInstance(this).getDao();

        callSingIn = findViewById(R.id.singin_screen);
        callSingIn.setOnClickListener(this);


        PasswordR = findViewById(R.id.pasnewwuser);
        Passwordconf = findViewById(R.id.passwordconfi);
        Bsingup = findViewById(R.id.singup);
        Bsingup.setOnClickListener(this);


    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(PasswordR.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singin_screen:
                Intent intent = new Intent(SingUpActivity.this, SingInActivity.class);
                startActivity(intent);
                break;

            case R.id.singup:
                final String password = PasswordR.getText().toString().trim();
                String passwordconf = Passwordconf.getText().toString().trim();
                if (!isEmpty()) {
                    if (password.equals(passwordconf)) {

                        progressDialog.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                User user = new User(password);
                                myDao.addUser(user);
                                progressDialog.dismiss();
                                Intent intent = new Intent(SingUpActivity.this, SingInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);

                    } else
                        Toast.makeText(SingUpActivity.this, "Mot de passe ne corresspondant pas ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SingUpActivity.this, "Champs vide", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }
    public void onBackPressed() {

        Intent intent5 = new Intent(SingUpActivity.this, SingInActivity.class);
        startActivity(intent5);
        finish();

    }
}