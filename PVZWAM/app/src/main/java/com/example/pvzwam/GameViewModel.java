package com.example.pvzwam;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

/**
 * The `GameViewModel` class is responsible for managing game data and logic in the PVZWackAMole game.
 * It is part of the Android ViewModel architecture and provides data for the game's user interface (UI)
 * and handles game events.
 */
public class GameViewModel extends ViewModel {

    /**
     * The current score
     */
    public int currentScore;

    /**
     * The high score
     */
    public int highScore;

    /**
     * The number of lives remaining
     */
    public int lives = 5;

    /**
     * Tells whether a zombie was missed or not
     */
    public boolean zombieMissed = false;
//    public int r;

    // Fields for time-related game parameters
    private long waitTime = 3000;
    private long waitTimeDecay = 10;
    private long waitTimeMinimum = 0;
    private long zombieTime = 3000;
    private long zombieTimeDecay = 100;
    private long zombieTimeMinimum = 500;

    /**
     * MutableLiveData for observing and updating zombie location
     */
    public MutableLiveData<Integer> zombieLocation;

    /**
     * Gets the MutableLiveData for observing the zombie location.
     *
     * @return The MutableLiveData for zombie location.
     */
    public MutableLiveData<Integer> getZombieLocation() {
        if (zombieLocation == null) {
            zombieLocation = new MutableLiveData<Integer>();
        }
        return zombieLocation;
    }

    // Handler for managing game event execution on the main thread
    Handler handler = new Handler(Looper.getMainLooper());

    long nextWaitTime;
    Runnable waitRunnable = new Runnable() {

        /**
         * Defines the logic for the waitRunnable, which handles missed zombies and updates wait time.
         */
        @Override
        public void run() {
            // Game logic for handling missed zombies and updating wait time
//            r = 1;
//            zombieLocation.setValue(1);
            if((zombieLocation.getValue() != null) && (zombieLocation.getValue() >= 1)) {
                zombieMissed = true;
                lives -= 1;
                zombieLocation.setValue(zombieLocation.getValue() * -1);
            }
            nextWaitTime = SystemClock.uptimeMillis() + Math.max(waitTimeMinimum, waitTime);
            handler.postAtTime(zombieRunnable, nextWaitTime);
        }
    };
    Random rand = new Random();
    long nextZombieTime;
    Runnable zombieRunnable = new Runnable() {

        /**
         * Defines the logic for the zombieRunnable, which generates zombies and updates zombie time.
         */
        @Override
        public void run() {
            // Game logic for generating zombies and updating zombie time
//            r = 2;
            zombieMissed = false;
            zombieLocation.setValue(rand.nextInt(16) + 1);
            nextZombieTime = SystemClock.uptimeMillis() + Math.max(zombieTimeMinimum, zombieTime);
            handler.postAtTime(waitRunnable, nextZombieTime);
        }
    };

    /**
     * Handles a zombie click event, updating game state and scores.
     */
    public void zombieClicked() {
        // Game logic for handling a zombie click event
        handler.removeCallbacks(waitRunnable);
        zombieMissed = false;
        currentScore += 1;
        zombieLocation.setValue(zombieLocation.getValue() * -1);
        waitTime -= waitTimeDecay;
        zombieTime -= zombieTimeDecay;
        handler.post(waitRunnable);
    }

}
