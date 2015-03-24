package org.macchiatow.tomato.ui;

import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class TomatoWidget implements StatusBarWidget {
    private volatile long value;
    private volatile int pomodoro = 0;

    private StatusBar statusBar;
    private WidgetPresentation presentation;
    private Timer timer;
    private TimerTask timerTask;
    private boolean paused = false;

    public void pomodoro(){
        value = 25 * 60 * 1000;
        startTimer();
    }

    private void startTimer(){
        this.timer = new Timer();
        this.timerTask = new TomatoTask();
        timer.schedule(timerTask, 1000, 1000);
    }

    public void shortBreak(){

    }

    public void longBreak(){

    }

    public void pauseContinue(){
        if (!paused){
            timer.cancel();
        } else {
            startTimer();
        }

        paused = !paused;
    }

    public void reset(){

    }

    @NotNull
    @Override
    public String ID() {
        return Initialization.id;
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType platformType) {
        if (presentation == null){
            presentation = new TomatoWidgetPresentation();
        }

        return presentation;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        this.statusBar = statusBar;
        update();
    }

    @Override
    public void dispose() {

    }

    private void update(){
        statusBar.updateWidget(Initialization.id);
    }

    class TomatoWidgetPresentation implements TextPresentation {
        @Nullable
        @Override
        public String getTooltipText() {
            return "Toolkit";
        }

        @Nullable
        @Override
        public Consumer<MouseEvent> getClickConsumer() {
            return null;
        }

        @NotNull
        @Override
        public String getText() {
            return String.format("%02d %02d:%02d", pomodoro, MILLISECONDS.toMinutes(value),
                    MILLISECONDS.toSeconds(value) - MINUTES.toSeconds(MILLISECONDS.toMinutes(value)));
        }

        @NotNull
        @Override
        public String getMaxPossibleText() {
            return "25:00";
        }

        @Override
        public float getAlignment() {
            return 0.5f;
        }
    }

    class TomatoTask extends TimerTask {
        @Override
        public void run() {
            value -= 1000;
            if (value < 0){
                pomodoro += 1;
                timer.cancel();
            }
            update();
        }
    }

}