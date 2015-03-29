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
package org.macchiatow.tomato.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vcs.VcsNotifier;
import com.intellij.openapi.vcs.roots.VcsRootProblemNotifier;
import com.intellij.ui.BalloonImpl;
import com.intellij.ui.LightColors;
import org.macchiatow.tomato.ui.TomatoNotification;

/**
 * Created by Togrul Mageramov on 3/23/2015.
 */
public class PomodoroAction extends DefaultAction {
    TomatoNotification notification;

    @Override
    public void actionPerformed(AnActionEvent e) {
        //widget.pomodoro();

        notification = new TomatoNotification("ffs", 2000);
        notification.notify(e.getProject());

    }

}
