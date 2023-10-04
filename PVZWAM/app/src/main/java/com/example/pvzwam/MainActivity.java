package com.example.pvzwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static java.lang.String.valueOf;

/**
 * Main activity for the game app. Brings the user to a home page
 * that displays high score and last score, with button that triggers game play.
 */
public class MainActivity extends AppCompatActivity {

    // Initializes textViews and score values
    private TextView highscore;
    private TextView lastscore;
    private int highScore;
    private int lastScore;

    /**
     * Initializes main activity page and values for high score and last score
     * @param savedInstanceState Bundle containing save state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highscore = findViewById(R.id.textView_hsNum);
        lastscore = findViewById(R.id.textView_lsNum);

        //Shared preferences that obtain values for high and last score
        SharedPreferences pref = getSharedPreferences("scores", Context.MODE_PRIVATE);
        highScore = pref.getInt("high_score", 0);
        lastScore = pref.getInt("last_score", 0);

        highscore.setText(valueOf(highScore));
        lastscore.setText(valueOf(lastScore));

    }

    /**
     * When user clicks the play button, the user is taken to the
     * GameActivity page. If error occurs, runtime exception is thrown
     * @param view play button is clicked
     */
    public void onPlayClicked(View view) {
        try {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}