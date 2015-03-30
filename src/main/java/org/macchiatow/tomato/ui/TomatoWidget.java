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

import com.google.common.base.Function;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.CountDownTimer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.macchiatow.tomato.Initialization.ID;

import static org.macchiatow.tomato.ui.TomatoNotification.showNotification;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class TomatoWidget implements StatusBarWidget {

    private static int NOTIFICATION_FADEOUT_TIME = 10 * 1000;

    private volatile long countDown;
    private volatile int pomodoro;
    private volatile boolean loop;

    private StatusBar statusBar;
    private TomatoWidgetPresentation presentation;

    private CountDownTimer pomodoroTimer;
    private CountDownTimer shortBreakTimer;
    private CountDownTimer longBreakTimer;
    private CountDownTimer activeTimer;

    private List<Function<Void, Void>> subscriptionOnFinish;
    private List<Function<Void, Void>> subscriptionOnActivate;

    public TomatoWidget() {
        presentation = new TomatoWidgetPresentation();
        subscriptionOnFinish = newArrayList();
        subscriptionOnActivate = newArrayList();

        pomodoroTimer = new CountDownTimer(25 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        updateUI();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        pomodoro += 1;
                        notifyFinish();
                        showNotification("+1 pomodoro", NOTIFICATION_FADEOUT_TIME);
                        if (loop) shortBreak();
                        return null;
                    }
                });

        shortBreakTimer = new CountDownTimer(5 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        updateUI();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        notifyFinish();
                        showNotification("Break finished", NOTIFICATION_FADEOUT_TIME);
                        if (loop) pomodoro();
                        return null;
                    }
                });

        longBreakTimer = new CountDownTimer(15 * 60 * 1000,
                new Function<Long, Void>() {
                    @Override
                    public Void apply(@Nullable Long aLong) {
                        countDown = aLong;
                        updateUI();
                        return null;
                    }
                },
                new Function<Void, Void>() {
                    @Override
                    public Void apply(@Nullable Void aVoid) {
                        notifyFinish();
                        showNotification("Break finished", NOTIFICATION_FADEOUT_TIME);
                        if (loop) pomodoro();
                        return null;
                    }
                });

        activeTimer = pomodoroTimer;
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
        updateUI();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private void startTimer(CountDownTimer timer){
        pomodoroTimer.reset();
        shortBreakTimer.reset();
        longBreakTimer.reset();
        activeTimer = timer;
        activeTimer.startPause();
        notifyActivate();
    }

    private void updateUI(){
        presentation.setPomodoro(pomodoro);
        presentation.setCountDown(countDown);
        statusBar.updateWidget(ID);
    }

    public void addSubscriberOnFinish(Function<Void, Void> f){
        subscriptionOnFinish.add(f);
    }

    public void addSubscriberOnActivate(Function<Void, Void> f){
        subscriptionOnActivate.add(f);
    }

    private void notifyFinish(){
        for (Function<Void, Void> f : subscriptionOnFinish){
            f.apply(null);
        }
    }

    private void notifyActivate(){
        for (Function<Void, Void> f : subscriptionOnActivate){
            f.apply(null);
        }
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
        updateUI();
    }

    @Override
    public void dispose() {}

}
