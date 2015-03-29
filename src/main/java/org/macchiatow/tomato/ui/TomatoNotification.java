package org.macchiatow.tomato.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.*;
import com.intellij.openapi.util.IconLoader;
import com.intellij.reference.SoftReference;
import com.intellij.ui.BalloonImpl;
import com.intellij.ui.LightColors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import static com.intellij.notification.NotificationDisplayType.STICKY_BALLOON;

/**
 * Created by Togrul Mageramov on 3/27/15.
 */
public class TomatoNotification extends Notification {

    private static final String TITLE = "Tomato";
    private Balloon balloon;

    private int milliseconds;

    public TomatoNotification(String content, int milliseconds) {
        super(TITLE, TITLE, content, NotificationType.INFORMATION);
        new NotificationGroup(TITLE, STICKY_BALLOON, true);
        this.milliseconds = milliseconds;

    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icon32.png");
    }

    public void setBalloon(@NotNull final Balloon balloon) {
        super.setBalloon(balloon);
        if (this.getBalloon() != null){

        }
    }
    @Override
    public void notify(Project p){
        super.notify(p);
        while (this.getBalloon() == null){}
        this.getBalloon().setAnimationEnabled(true);
        ((BalloonImpl)this.getBalloon()).startFadeoutTimer(3000);
    }

}
