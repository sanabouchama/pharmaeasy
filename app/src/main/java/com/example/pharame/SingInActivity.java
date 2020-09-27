package com.example.pharame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Build;
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

public class SingInActivity extends AppCompatActivity implements View.OnClickListener {


    Button callSingUp;
    Button Bsingin;
    EditText PasswordS;

    private MyAppDataBase dataBase;
    private MyDao myDao;
    private ProgressDialog progressDialog;

    public static MyAppDataBase myAppDatabase;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Check User...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);


        myDao = MyAppDataBase.creatmydbInstance(this).getDao();

        callSingUp = findViewById(R.id.singup_screen);
        callSingUp.setOnClickListener(this);

        Bsingin = findViewById(R.id.singin);
        Bsingin.setOnClickListener(this);

        PasswordS = findViewById(R.id.paswuser);

    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(PasswordS.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singup_screen:
                final Intent intent = new Intent(SingInActivity.this, SingUpActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.singin:
                final String passwords = PasswordS.getText().toString().trim();
                if (!emptyValidation()) {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user =     myDao.getUser(passwords);
                            if (user != null) {

                                Intent i = new Intent(SingInActivity.this, HomeActivity.class);
                                i.putExtra("password",user.getPassword());
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(SingInActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);

                } else {
                    Toast.makeText(SingInActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

                break;

        }


    }
    public void onBackPressed() {

        Intent intent11 = new Intent(SingInActivity.this, SingInActivity.class);
        startActivity(intent11);
        finish();

    }
}