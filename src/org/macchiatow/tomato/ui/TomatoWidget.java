package org.macchiatow.tomato.ui;

import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    String id = UUID.randomUUID().toString();
    Date date = new Date();

    StatusBar statusBar;
    WidgetPresentation presentation;
    String timerValue = "00:00";
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Date now = new Date();
            timerValue = String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes(now.getTime() - date.getTime()),
                    TimeUnit.MILLISECONDS.toSeconds(now.getTime() - date.getTime()) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - date.getTime()))
            );
            update();
        }
    };

    @NotNull
    @Override
    public String ID() {
        return id;
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
        timer.schedule(timerTask, 1000, 1000);
        update();
    }

    @Override
    public void dispose() {

    }

    private void update(){
        statusBar.updateWidget(id);
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
            return timerValue;
        }

        @NotNull
        @Override
        public String getMaxPossibleText() {
            return "25:00";
        }

        @Override
        public float getAlignment() {
            return 0;
        }
    }
}