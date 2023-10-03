package com.example.pvzwam;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.String.valueOf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * The `GameActivity` class represents the main game screen of the Zombie game.
 * It manages gameplay, user interactions, and audio playback.
 *
 * This class includes methods for initializing game components, handling user clicks
 * on zombies, playing random zombie groan sounds, and ending the game when necessary.
 */
public class GameActivity extends AppCompatActivity {

    // Initialize MediaPlayer instances for game sound effects
    private MediaPlayer groan;
    private MediaPlayer groan2;
    private MediaPlayer groan3;
    private MediaPlayer groan4;
    private MediaPlayer groan5;
    private MediaPlayer groan6;
    private MediaPlayer hit;
    private MediaPlayer lawnMower;
    private MediaPlayer mainTheme;

    private MediaPlayer scream;

    // ViewModel for game data
    private GameViewModel model;

    // UI elements
    private ImageView zombie;
    private TextView currentScore;
    private TextView highScore;
    private ImageView lawnMower1;
    private ImageView lawnMower2;
    private ImageView lawnMower3;
    private ImageView lawnMower4;

    /**
     * Called when the activity is created. Initializes game resources and UI elements.
     *
     * @param savedInstanceState A Bundle containing saved state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Initialize MediaPlayer instances for game sound effects
        groan = MediaPlayer.create(this, R.raw.groan);
        groan2 = MediaPlayer.create(this, R.raw.groan2);
        groan3 = MediaPlayer.create(this, R.raw.groan3);
        groan4 = MediaPlayer.create(this, R.raw.groan4);
        groan5 = MediaPlayer.create(this, R.raw.groan5);
        groan6 = MediaPlayer.create(this, R.raw.groan6);
        hit = MediaPlayer.create(this, R.raw.hit);
        lawnMower = MediaPlayer.create(this, R.raw.lawn_mower);
        mainTheme = MediaPlayer.create(this, R.raw.main_theme);
        scream = MediaPlayer.create(this, R.raw.scream);

        // Start the main theme music
        mainTheme.start();

        // Initialize the ViewModel
        model = new ViewModelProvider(this).get(GameViewModel.class);

        // Load high score from SharedPreferences
        SharedPreferences pref = getSharedPreferences("scores", Context.MODE_PRIVATE);
        model.highScore = Math.max(pref.getInt("high_score", 0), model.highScore);

        // Initialize UI elements
        currentScore = findViewById(R.id.game_text_currentScore);
        highScore = findViewById(R.id.game_text_highScore);
        lawnMower1 = findViewById(R.id.game_image_lawnMower1);
        lawnMower2 = findViewById(R.id.game_image_lawnMower2);
        lawnMower3 = findViewById(R.id.game_image_lawnMower3);
        lawnMower4 = findViewById(R.id.game_image_lawnMower4);

        // Set the high score text
        highScore.setText(valueOf(model.highScore));

//        if(model.r == 1) {
//            currentScore.setBackgroundColor(RED);
//        }
//        else if(model.r == 2) {
//            currentScore.setBackgroundColor(GREEN);
//        }
//        else {
//            currentScore.setBackgroundColor(BLACK);
//        }

        // Define an observer to update the UI based on the zombie's location
        final Observer<Integer> zombieLocationObserver = new Observer<Integer>() {
            /**
             * Called when a zombie's location changes or is removed, updating the game's UI elements and state.
             *
             * @param newZombieLocation  The new location of the zombie, indicating its position on the screen, or where to remove a zombie from, if negative
             */
            @Override
            public void onChanged(@Nullable final Integer newZombieLocation) {
//                if(model.r == 1) {
//                    currentScore.setBackgroundColor(RED);
//                }
//                else if(model.r == 2) {
//                    currentScore.setBackgroundColor(GREEN);
//                }
//                else {
//                    currentScore.setBackgroundColor(BLACK);
//                }
                if(abs(newZombieLocation) == 1) {
                    zombie = findViewById(R.id.game_image_wallnut1);
                }
                else if(abs(newZombieLocation) == 2) {
                    zombie = findViewById(R.id.game_image_wallnut2);
                }
                else if(abs(newZombieLocation) == 3) {
                    zombie = findViewById(R.id.game_image_wallnut3);
                }
                else if(abs(newZombieLocation) == 4) {
                    zombie = findViewById(R.id.game_image_wallnut4);
                }
                else if(abs(newZombieLocation) == 5) {
                    zombie = findViewById(R.id.game_image_wallnut5);
                }
                else if(abs(newZombieLocation) == 6) {
                    zombie = findViewById(R.id.game_image_wallnut6);
                }
                else if(abs(newZombieLocation) == 7) {
                    zombie = findViewById(R.id.game_image_wallnut7);
                }
                else if(abs(newZombieLocation) == 8) {
                    zombie = findViewById(R.id.game_image_wallnut8);
                }
                else if(abs(newZombieLocation) == 9) {
                    zombie = findViewById(R.id.game_image_wallnut9);
                }
                else if(abs(newZombieLocation) == 10) {
                    zombie = findViewById(R.id.game_image_wallnut10);
                }
                else if(abs(newZombieLocation) == 11) {
                    zombie = findViewById(R.id.game_image_wallnut11);
                }
                else if(abs(newZombieLocation) == 12) {
                    zombie = findViewById(R.id.game_image_wallnut12);
                }
                else if(abs(newZombieLocation) == 13) {
                    zombie = findViewById(R.id.game_image_wallnut13);
                }
                else if(abs(newZombieLocation) == 14) {
                    zombie = findViewById(R.id.game_image_wallnut14);
                }
                else if(abs(newZombieLocation) == 15) {
                    zombie = findViewById(R.id.game_image_wallnut15);
                }
                else if(abs(newZombieLocation) == 16) {
                    zombie = findViewById(R.id.game_image_wallnut16);
                }

                // Handle zombie appearing
                if(newZombieLocation > 0) {
                    playRandomGroan();
                    zombie.setImageResource(R.drawable.zombie_eating);
                    zombie.setPadding(0, 0, 0, 0);

                }
                // Handle zombie being removed
                else {
                    if (model.lives != 0) {
                        // Play lawn mower sound if zombie is missed and lives are not depleted
                        if (model.zombieMissed) {
                            lawnMower.start();
                        }
                        // Play hit sound if zombie is clicked
                        else {
                            hit.start();
                        }
                    }
                    zombie.setImageResource(R.drawable.wallnut);
                    zombie.setPadding(2, 70, 0, 0);
                    zombie = null;
                }

                // Update the current score
                currentScore.setText(valueOf(model.currentScore));

                // Update the high score
                model.highScore = Math.max(model.currentScore, model.highScore);
                highScore.setText(valueOf(model.highScore));

                // Handle lawn mower visibility based on remaining lives
                if(model.lives <= 4) {
                    lawnMower4
                            .setVisibility(View.INVISIBLE);
//                            .setImageResource(R.drawable.spent_lawn_mower);
                }
                if(model.lives <= 3) {
                    lawnMower3
                            .setVisibility(View.INVISIBLE);
//                            .setImageResource(R.drawable.spent_lawn_mower);
                }
                if(model.lives <= 2) {
                    lawnMower2
                            .setVisibility(View.INVISIBLE);
//                            .setImageResource(R.drawable.spent_lawn_mower);
                }
                if(model.lives == 1) {
                    lawnMower1
                            .setVisibility(View.INVISIBLE);
//                            .setImageResource(R.drawable.spent_lawn_mower);
                }

                // End the game if there are no lives left
                if(model.lives == 0) {
                    endGame(
                            pref
                    );
                }

                // Restarts music if it reached the end
                if (mainTheme != null && !mainTheme.isPlaying()) {
                    mainTheme.start();
                }
            }
        };

        // Observe changes in the zombie's location
        model.getZombieLocation().observe(this, zombieLocationObserver);

        // Start the game loop
        model.handler.post(model.waitRunnable);
    }

    /**
     * Called when a Wall-Nut spot is clicked. Handles user interaction with the game.
     *
     * @param view The Wall-Nut that was clicked.
     */
    public void onWallnutClicked(View view) {
        try {
            if((zombie != null) && (zombie == view)) {
                model.zombieClicked();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Random rand = new Random();
    int randomGroanID;

    /**
     * Plays a random zombie groan sound effect.
     */
    public void playRandomGroan() {
        randomGroanID = rand.nextInt(6);
        if(randomGroanID == 0) {
            groan.start();
        }
        else if(randomGroanID == 1) {
            groan2.start();
        }
        else if(randomGroanID == 2) {
            groan3.start();
        }
        else if(randomGroanID == 3) {
            groan4.start();
        }
        else if(randomGroanID == 4) {
            groan5.start();
        }
        else if(randomGroanID == 5) {
            groan6.start();
        }
    }

    /**
     * Ends the game, saves the high score, and transitions to the main menu.
     *
     * @param pref The SharedPreferences instance for storing game data.
     */
    public void endGame(SharedPreferences pref) {

        // Remove callbacks from the game handler
        model.handler.removeCallbacks(model.waitRunnable);
        model.handler.removeCallbacks(model.zombieRunnable);

        // Save the last score and high score to SharedPreferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("last_score", model.currentScore);
        editor.putInt("high_score", model.highScore);
        editor.apply();

        // Stop the main theme music and play a scream sound effect
        mainTheme.stop();
        scream.start();

        // Transition to the main menu activity
        Intent endGame = new Intent(this, MainActivity.class);
        startActivity(endGame);
    }

    /**
     * Called when the activity is stopped. Releases MediaPlayer resources.
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (mainTheme != null) {
            mainTheme.release();
            mainTheme = null;
        }
        if (groan != null) {
            groan.release();
            groan = null;
        }
        if (groan2 != null) {
            groan2.release();
            groan2 = null;
        }
        if (groan3 != null) {
            groan3.release();
            groan3 = null;
        }
        if (groan4 != null) {
            groan4.release();
            groan4 = null;
        }
        if (groan5 != null) {
            groan5.release();
            groan5 = null;
        }
        if (groan6 != null) {
            groan6.release();
            groan6 = null;
        }
        if (hit != null) {
            hit.release();
            hit = null;
        }
        if (lawnMower != null) {
            lawnMower.release();
            lawnMower = null;
        }
        //TODO: Maybe fix music quality
        if (mainTheme != null) {
            mainTheme.release();
            mainTheme = null;
        }
        //TODO: Maybe fix scream cutoff
        if (scream != null) {
            scream.release();
            scream = null;
        }
    }

}