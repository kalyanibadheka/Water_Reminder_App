package com.example.water_reminder_app;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Calendar;

public class setting extends AppCompatActivity {

    Spinner spin;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imageView = findViewById(R.id.imageView);
        notification_cannel();
        pendingIntent = pendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(this, Broadcast_Receiver.class), PendingIntent.FLAG_IMMUTABLE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        spin = findViewById(R.id.spin);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iback = new Intent(setting.this, MainActivity.class);
                startActivity(iback);
            }
        });
        String[] spinitem = {"1 Minute","30 Minutes","1 Hour","2 Hour", "Stop Notification"};

        ArrayAdapter<String> adt = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinitem);
        spin.setAdapter(adt);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String time = spinitem[i];
                Log.d(TAG, time);
                if (time == "2 Hour") {
                    set_notification_alarm(2 * 60 * 60 * 1000);
                }
                if (time == "1 Hour") {
                    set_notification_alarm(60 * 60 * 1000);
                }
                if (time == "30 Minutes") {
                    set_notification_alarm(30 * 60 * 1000);
                }
                if (time == "1 Minute") {
                    set_notification_alarm(60 * 1000);
                }
                if (time == "Stop Notification") {
                    cancel_notification_alarm();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
        public void set_notification_alarm (long interval){
            long triggeratmillis = System.currentTimeMillis() + interval;

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);

            if(Build.VERSION.SDK_INT >= 23) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, pendingIntent);
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggeratmillis, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggeratmillis, pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggeratmillis, pendingIntent);
            }

        }
        public void cancel_notification_alarm(){
            alarmManager.cancel(pendingIntent);
        }
        private void notification_cannel(){
            CharSequence name= "Reminder";
            String desc = "Reminder Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("Notification",name,importance);
                channel.setDescription(desc);
                NotificationManager notiman = getSystemService(NotificationManager.class);
                notiman.createNotificationChannel(channel);

            }

        }

}