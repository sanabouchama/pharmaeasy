package com.example.pharame;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import Data.MyAppDataBase;

public class Reminder extends AppCompatActivity {
    Toolbar toolbar;
    TimePicker timePickerdjj, timePickerfjj;
    Button btnconf;
    int mhourdj, mminutdj, mhourfj, mminutfj;

    MyAppDataBase dataBase;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        toolbar = findViewById(R.id.toolbar20);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });


        timePickerdjj = findViewById(R.id.timepickerdj);
        timePickerfjj = findViewById(R.id.timepickerfj);
        btnconf = findViewById(R.id.button2);



        dataBase = MyAppDataBase.creatmydbInstance(this);


        timePickerdjj.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {@Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mhourdj = hourOfDay;
                mminutdj = minute;
        }

        });




        timePickerfjj.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mhourfj = hourOfDay;
                mminutfj = minute;




            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.close:
                Intent intent = new Intent(Reminder.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return true;
    }


    public void setTimerdj(View view) {

        Intent intent = new Intent(Reminder.this, HomeActivity.class);
        startActivity(intent);
        finish();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);


        cal_alarm.set(Calendar.HOUR_OF_DAY, mhourdj);
        cal_alarm.set(Calendar.MINUTE, mminutdj);
        cal_alarm.set(Calendar.SECOND, 0);


        if (cal_alarm.before(cal_now)) {
            cal_alarm.add(Calendar.DATE, 1);
        }



        Intent i = new Intent(Reminder.this, MyBroadcastReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 24244, i, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                cal_alarm.getTimeInMillis(),
                24*60*60*1000,
                pendingIntent);



        Date date2 = new Date();
        Calendar cal_alarm2 = Calendar.getInstance();
        Calendar cal_now2 = Calendar.getInstance();

        cal_now2.setTime(date2);
        cal_alarm2.setTime(date2);


        cal_alarm2.set(Calendar.HOUR_OF_DAY, mhourfj);
        cal_alarm2.set(Calendar.MINUTE, mminutfj);
        cal_alarm2.set(Calendar.SECOND, 0);


        if (cal_alarm2.before(cal_now2)) {
            cal_alarm2.add(Calendar.DATE, 1);
        }



        Intent i2 = new Intent(Reminder.this, MyBroadcastReciver2.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(Reminder.this, 24244, i2, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent2);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                cal_alarm2.getTimeInMillis(),
                24*60*60*1000,
                pendingIntent2);



    }

}

