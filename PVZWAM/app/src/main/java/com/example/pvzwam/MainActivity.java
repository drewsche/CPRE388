package com.example.pvzwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

//    Button play = findViewById(R.id.button_Play);
    TextView highscore = findViewById(R.id.textView_hsNum);
    TextView lastscore = findViewById(R.id.textView_lsNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
//        highscore.setText(valueOf(pref.getInt("high_score", 0)));
//        lastscore.setText(valueOf(pref.getInt("last_score", 0)));

    }

    public void onPlayClicked(View view) {
        try {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}