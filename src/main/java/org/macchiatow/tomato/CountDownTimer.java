/*
 * Copyright 2015 Togrul Mageramov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.macchiatow.tomato;

import com.google.common.base.Function;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Togrul Mageramov on 3/24/2015.
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

