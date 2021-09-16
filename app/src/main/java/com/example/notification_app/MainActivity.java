package com.example.notification_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int notification =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.set_benID).setOnClickListener(this);
        findViewById(R.id.cancel_btnID).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText editText=(EditText)findViewById(R.id.editTextId);
        TimePicker timePicker=(TimePicker)findViewById(R.id.timePickerId);

        Intent intent= new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notification",notification);
        intent.putExtra("todo",editText.getText().toString());

        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this,0,
                intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.set_benID:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //Create time.

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();


                //SET ALARM;
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;


            case R.id.cancel_btnID:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
