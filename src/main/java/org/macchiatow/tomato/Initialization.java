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

    Project project;
    StatusBar statusBar;

    public Initialization(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null) {
            final TomatoWidget w= new TomatoWidget();
            statusBar.addWidget(w, "after " + (SystemInfo.isMac ? "Encoding" : "InsertOverwrite"), project);
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
