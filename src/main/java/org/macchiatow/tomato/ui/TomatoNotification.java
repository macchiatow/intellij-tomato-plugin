package org.macchiatow.tomato.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Togrul Mageramov on 3/27/15.
 */
public class TomatoNotification extends Notification {

    private static final String TITLE = "Tomato";

    private long milliseconds;

    private Timer timer;
    private TimerTask task;

    public TomatoNotification(String content, long milliseconds) {
        super(TITLE, TITLE, content, NotificationType.INFORMATION);
        this.milliseconds = milliseconds;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icon32.png");
    }

    @Override
    public void notify(@Nullable Project project) {
        super.notify(project);

        task = new TimerTask() {
            @Override
            public void run() {
               hideBalloon();
            }
        };

        timer = new Timer();
        timer.schedule(task, milliseconds, 1);
    }
}
