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
package org.macchiatow.tomato;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;
import org.macchiatow.tomato.ui.TomatoWidget;

import java.util.UUID;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class Initialization implements ProjectComponent {

    public static String ID = UUID.randomUUID().toString();
    public static Project PROJECT;

    private StatusBar statusBar;

    public Initialization(Project project) {
        this.PROJECT = project;
    }

    @Override
    public void projectOpened() {
        statusBar = WindowManager.getInstance().getStatusBar(PROJECT);
        if (statusBar != null) {
            final TomatoWidget w= new TomatoWidget();
            statusBar.addWidget(w, "after " + (SystemInfo.isMac ? "Encoding" : "InsertOverwrite"), PROJECT);
        }
    }

    @NotNull
    public String getComponentName() {
        return "TomatoPlugin";
    }

    @Override
    public void initComponent() {}

    @Override
    public void disposeComponent() {}

    @Override
    public void projectClosed() {}
}
