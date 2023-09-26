package com.example.water_reminder_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class user_detail extends AppCompatActivity {
    EditText edtage,edtweight;
    Button btnsubmit,edtsleep,edtwake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        edtage = findViewById(R.id.edtage);
        edtwake = findViewById(R.id.edtwake);
        edtsleep = findViewById(R.id.edtsleep);
        btnsubmit = findViewById(R.id.btnsubmit);
        edtweight = findViewById(R.id.edtweight);

//        SharedPreferences getshrd = getSharedPreferences("userdetail",MODE_PRIVATE);



//        String[] age= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};

        edtwake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog tpick;
                tpick = new TimePickerDialog(user_detail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                        edtwake.setText(selectedhour + ":" + selectedminute);
                    }
                },hour,minute,false);
                tpick.setTitle("Select Time");
                tpick.show();
            }
        });

        edtsleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog tpick;
                tpick = new TimePickerDialog(user_detail.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                        edtsleep.setText(selectedhour + ":" + selectedminute);
                    }
                },hour,minute,false);
                tpick.setTitle("Select Time");
                tpick.show();
            }
        });
        Intent inext = new Intent(user_detail.this, MainActivity.class);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strwake = edtwake.getText().toString();
//                long wake = Long.parseLong(strwake);
                String strsleep = edtsleep.getText().toString();
//                long sleep = Time.parse(strsleep);
                String strage = edtage.getText().toString();
                int age = Integer.parseInt(strage);
                String strweight = edtweight.getText().toString();
                int weight = Integer.parseInt(strweight);

                SharedPreferences shrdetail = getSharedPreferences("userdetail",MODE_PRIVATE);
                SharedPreferences.Editor editor = shrdetail.edit();

                editor.putInt("age",age);
                editor.putInt("weight",weight);
                editor.putString("wake",strwake);
                editor.putString("sleep",strsleep);
                editor.putBoolean("flag",true);

                editor.commit();

                startActivity(inext);

            }
        });
    }
}