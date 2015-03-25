package org.macchiatow.tomato.ui;

import com.google.common.base.Function;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.CountDownTimer;

import static org.macchiatow.tomato.Initialization.ID;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class TomatoWidget implements StatusBarWidget {
    private volatile long countDown;
    private volatile int pomodoro;
    private volatile boolean loop;

    private StatusBar statusBar;
    private TomatoWidgetPresentation presentation;
    private CountDownTimer pomodoroTimer;
    private CountDownTimer shortBreakTimer;
    private CountDownTimer longBreakTimer;
    private CountDownTimer activeTimer;

    public TomatoWidget() {
        presentation = new TomatoWidgetPresentation();

        pomodoroTimer = new CountDownTimer(25 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        pomodoro += 1;
                        if (loop) shortBreak();
                        return null;
                    }
                });

        shortBreakTimer = new CountDownTimer(5 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        if (loop) pomodoro();
                        return null;
                    }
                });

        longBreakTimer = new CountDownTimer(15 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        if (loop) pomodoro();
                        return null;
                    }
                });
    }

    public void pomodoro(){
        startTimer(pomodoroTimer);
    }

    public void shortBreak(){
        startTimer(shortBreakTimer);
    }

    public void longBreak(){
        startTimer(longBreakTimer);
    }

    public void pauseContinue(){
        activeTimer.startPause();
    }

    public void setPomodoro(int pomodoro) {
        this.pomodoro = pomodoro;
        update();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private void startTimer(CountDownTimer timer){
        pomodoroTimer.reset();
        shortBreakTimer.reset();
        longBreakTimer.reset();
        activeTimer = timer;
        pauseContinue();
    }

    private void update(){
        presentation.setPomodoro(pomodoro);
        presentation.setCountDown(countDown);
        statusBar.updateWidget(ID);
    }

    @NotNull
    @Override
    public String ID() {
        return ID;
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType platformType) {
        return presentation;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        this.statusBar = statusBar;
        update();
    }

    @Override
    public void dispose() {}

}