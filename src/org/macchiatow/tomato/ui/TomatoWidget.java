package org.macchiatow.tomato.ui;

import com.google.common.base.Function;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.CountDownTimer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.awt.event.MouseEvent;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class TomatoWidget implements StatusBarWidget {
    private volatile long value;
    private volatile int pomodoro = 0;

    private StatusBar statusBar;
    private WidgetPresentation presentation;
    private CountDownTimer pomodoroTimer;
    private CountDownTimer shortBreakTimer;
    private CountDownTimer longBreakTimer;
    private CountDownTimer activeTimer;

    public TomatoWidget() {
        pomodoroTimer = new CountDownTimer(25 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        value = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        pomodoro += 1;
                        return null;
                    }
                });

        shortBreakTimer = new CountDownTimer(5 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        value = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        return null;
                    }
                });

        longBreakTimer = new CountDownTimer(15 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        value = aLong;
                        update();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        return null;
                    }
                });
    }

    public void pomodoro(){
        pomodoroTimer.reset();
        shortBreakTimer.reset();
        longBreakTimer.reset();
        activeTimer = pomodoroTimer;
        pomodoroTimer.startPause();
    }

    public void shortBreak(){
        pomodoroTimer.reset();
        shortBreakTimer.reset();
        longBreakTimer.reset();
        activeTimer = shortBreakTimer;
        shortBreakTimer.startPause();
    }

    public void longBreak(){
        pomodoroTimer.reset();
        shortBreakTimer.reset();
        longBreakTimer.reset();
        activeTimer = longBreakTimer;
        longBreakTimer.startPause();
    }

    public void pauseContinue(){
        activeTimer.startPause();
    }

    private void update(){
        statusBar.updateWidget(Initialization.ID);
    }

    @NotNull
    @Override
    public String ID() {
        return Initialization.ID;
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
    public void dispose() {}

    class TomatoWidgetPresentation implements TextPresentation {
        @NotNull
        @Override
        public String getText() {
            return String.format("%02d %02d:%02d", pomodoro, MILLISECONDS.toMinutes(value),
                    MILLISECONDS.toSeconds(value) - MINUTES.toSeconds(MILLISECONDS.toMinutes(value)));
        }

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
        public String getMaxPossibleText() {
            return "99 25:00";
        }

        @Override
        public float getAlignment() {
            return 0.5f;
        }
    }


}