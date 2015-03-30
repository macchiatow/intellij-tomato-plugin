package org.macchiatow.tomato.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.BalloonImpl;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.Initialization;

import javax.swing.*;

import static com.intellij.notification.NotificationDisplayType.STICKY_BALLOON;

/**
 * Created by Togrul Mageramov on 3/27/15.
 */
public class TomatoNotification extends Notification {

    private static final String TITLE = "Tomato";

    private int fadeoutTime;

    public static void showNotification(String message, int fadeoutTime){
        new TomatoNotification(message, fadeoutTime).showNotification();
    }

    private TomatoNotification(String message, int fadeoutTime) {
        super(TITLE, TITLE, message, NotificationType.INFORMATION);
        new NotificationGroup(TITLE, STICKY_BALLOON, true);
        this.fadeoutTime = fadeoutTime;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icon32.png");
    }

    private void showNotification(){
        notify(Initialization.PROJECT);
        while (this.getBalloon() == null){}
        this.getBalloon().setAnimationEnabled(true);
        ((BalloonImpl)this.getBalloon()).startFadeoutTimer(fadeoutTime);
    }

}
