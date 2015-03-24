package org.macchiatow.tomato.ui;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Togrul Mageramov on 3/22/15.
 */
public class Initialization implements ProjectComponent {
    static String id = UUID.randomUUID().toString();

    Project project;
    StatusBar statusBar;

    public Initialization(Project project) {
        this.project = project;
    }

    public void initComponent() {

    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "TomatoPlugin";
    }

    @Override
    public void projectOpened() {
        statusBar= WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null) {
            final TomatoWidget w= new TomatoWidget();
            statusBar.addWidget(w, "after " + (SystemInfo.isMac ? "Encoding" : "InsertOverwrite"), project);
        }
    }

    @Override
    public void projectClosed() {

    }



}
