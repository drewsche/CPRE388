package com.example.pvzwam;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private long startTime = 0;
    private long stopTime = 0;

    private MutableLiveData<Long> elapsedTime;
    public MutableLiveData<Long> getElapsedTime() {
        if (elapsedTime == null) {
            elapsedTime = new MutableLiveData<Long>();
        }
        return elapsedTime;
    }

    Handler handler = new Handler(Looper.getMainLooper());
    long nextTime;
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            elapsedTime.setValue(stopTime + SystemClock.uptimeMillis() - startTime);
            nextTime += 10;
            handler.postAtTime(this, nextTime);
        }
    };
//    public void startStop() {
//        if() {
//            startTime = SystemClock.uptimeMillis();
//            nextTime = startTime;
//            handler.postAtTime(timerRunnable, nextTime);
//        }
//        else {
//            stopTime = elapsedTime.getValue();
//            handler.removeCallbacks(timerRunnable);
//        }
//    }

    public void reset() {
        startTime = SystemClock.uptimeMillis();
        stopTime = 0;
        elapsedTime.setValue(0L);
    }

    public void zombieAppearAt(int wallnutIDNum) {

    }

}
