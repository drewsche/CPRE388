package com.example.pvzwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button play = findViewById(R.id.button_Play);
    TextView highscore = findViewById(R.id.textView_hsNum);
    TextView lastscore = findViewById(R.id.textView_lsNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        highscore.setText(pref.getInt("high_score", 0));
        lastscore.setText(pref.getInt("last_score", 0));


    }
}