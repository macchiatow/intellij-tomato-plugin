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

import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by Togrul Mageramov on 3/24/15.
 */
class TomatoWidgetPresentation implements StatusBarWidget.TextPresentation {

    private volatile long pomodoro;
    private volatile long countDown;

    @NotNull
    @Override
    public String getText() {
        return String.format("%d %02d:%02d", pomodoro, MILLISECONDS.toMinutes(countDown),
                MILLISECONDS.toSeconds(countDown) - MINUTES.toSeconds(MILLISECONDS.toMinutes(countDown)));
    }

    @Nullable
    @Override
    public String getTooltipText() {
        return "Tomato Timer";
    }

    @Override
    public float getAlignment() {
        return 0.5f;
    }

    @NotNull
    @Override
    public String getMaxPossibleText() {
        return "99 25:00";
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

}