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
package org.macchiatow.tomato.ui;

import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.ui.popup.list.ListPopupImpl;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by Togrul Mageramov on 3/24/15.
 */
class TomatoWidgetPopupPresentation implements StatusBarWidget.MultipleTextValuesPresentation {

    private volatile long pomodoro;
    private volatile long countDown;

    @Nullable
    @Override
    public String getTooltipText() {
        return "Tomato Timer";
    }

    @Nullable
    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return null;
    }

    public void setPomodoro(long pomodoro) {
        this.pomodoro = pomodoro;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    @Nullable
    @Override
    public ListPopup getPopupStep() {
        ListPopupStep<String> step = new BaseListPopupStep<String>(null, new String[]{"Pomodoro", "Short break"});
        ListPopup popup = new ListPopupImpl(step);
        return popup;
    }

    @Nullable
    @Override
    public String getSelectedValue() {
        return String.format("%d %02d:%02d", pomodoro, MILLISECONDS.toMinutes(countDown),
                MILLISECONDS.toSeconds(countDown) - MINUTES.toSeconds(MILLISECONDS.toMinutes(countDown)));
    }

    @NotNull
    @Override
    public String getMaxValue() {
        return "99 25:00";
    }
}