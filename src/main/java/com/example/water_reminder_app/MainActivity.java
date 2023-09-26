package com.example.water_reminder_app;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView set,btnplus,btnminus;
    EditText edtxt;
    TextView txt,realprog;
    Button drink;
    int pgr = 0;
    ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set = findViewById(R.id.setting);
        txt = findViewById(R.id.text_view_progress);
        btnplus = findViewById(R.id.btnplus);
        btnminus = findViewById(R.id.btnminus);
        edtxt = findViewById(R.id.edtxt);
        drink = findViewById(R.id.drink);
        realprog = findViewById(R.id.realprog);
        progress_bar = findViewById(R.id.progress_bar);


        updateprogress();

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iset = new Intent(MainActivity.this, setting.class);
                startActivity(iset);


            }
        });

        SharedPreferences shrd = getSharedPreferences("userdetail", MODE_PRIVATE);
        int weight = shrd.getInt("weight", 0);

        int calc = (int) (weight * 0.033 * 1000);
        String wt = String.valueOf(calc);
        Log.d(TAG, wt);
        txt.setText(wt);
//        pgr = calc;


        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intake = edtxt.getText().toString();
                int intk = Integer.parseInt(intake);
                if (intk >= 0 && intk <= 690) {
                    intk += 10;
                    String sint = String.valueOf(intk);
                    edtxt.setText(sint);
                }
                if (intk == 0 || intk == 700) {
                    String sint = String.valueOf(intk);
                    edtxt.setText(sint);
                }


            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intake = edtxt.getText().toString();
                int intk = Integer.parseInt(intake);
                if (intk >= 10 && intk <= 700) {
                    intk -= 10;
                    String sint = String.valueOf(intk);
                    edtxt.setText(sint);
                }
                if (intk == 0 || intk == 700) {
                    String sint = String.valueOf(intk);
                    edtxt.setText(sint);
                }

//                if (pgr >= 10) {
//                    pgr -= 10;
//                    updateprogress();
//                }
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curnt = realprog.getText().toString();
                String intake = edtxt.getText().toString();
                String goal = txt.getText().toString();
                int icurnt = Integer.parseInt(curnt);
                int iintk = Integer.parseInt(intake);
                int igoal = Integer.parseInt(goal);
                if (icurnt >= igoal) {
                    Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                }
                int progress = icurnt + iintk;
                String prog = String.valueOf(progress);
                realprog.setText(prog);


                int temp = 100;
                if (pgr <= temp) {
                    int perc = iintk*100/calc;
                    Log.d(TAG, String.valueOf(perc));
                    pgr += perc;
                    Log.d(TAG, String.valueOf(pgr));
                    updateprogress();
                }
            }
        });
    }
    public void updateprogress(){
        progress_bar.setProgress(pgr);
    }
}
