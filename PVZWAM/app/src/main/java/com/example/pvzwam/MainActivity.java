package com.example.pvzwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {


//    Button play = findViewById(R.id.button_Play);

    private TextView highscore;
    private TextView lastscore;
    private int highScore;

    private int lastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        highscore = findViewById(R.id.textView_hsNum);
        lastscore = findViewById(R.id.textView_lsNum);

        SharedPreferences pref = getSharedPreferences("scores", Context.MODE_PRIVATE);
        highScore = pref.getInt("high_score", 0);
        lastScore = pref.getInt("last_score", 0);

        highscore.setText(valueOf(highScore));
        lastscore.setText(valueOf(lastScore));

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