package org.macchiatow.tomato;

import com.google.common.base.Function;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Togrul Mageramov on 24.03.2015.
 */
public class CountDownTimer {
    private long value;
    private long creationValue;
    private boolean paused;
    private boolean finished;
    private Timer timer;
    private Function<Long, Void> onTick;
    private Function<Void, Void> onFinished;

    private static long ONE_SECOND = 1000;

    public CountDownTimer(long milliseconds, Function<Long, Void> onTick, Function<Void, Void> onFinished){
        this.onTick = onTick;
        this.onFinished = onFinished;
        this.creationValue = milliseconds;
        reset();
    }

    public void startPause(){
        if (paused){
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (value > 0) {
                        value -= ONE_SECOND;
                    } else {
                        onFinish();
                        timer.cancel();
                    }
                    onTick();
                }};
            timer = new Timer();
            timer.schedule(task, ONE_SECOND, 1000);
        } else {
            timer.cancel();
        }
        paused = !paused;
    }

    public void reset(){
        finished = false;
        paused = true;
        value = creationValue;
        if (timer != null) timer.cancel();
    }

    private void onTick(){
        onTick.apply(value);
    }

    private void onFinish(){
        if (!finished) {
            onFinished.apply(null);
            finished = !finished;
        }
    }

}

