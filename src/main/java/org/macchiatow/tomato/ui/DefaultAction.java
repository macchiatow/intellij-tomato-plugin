package org.macchiatow.tomato.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

import static org.macchiatow.tomato.Initialization.ID;

/**
 * Created by Togrul Mageramov on 3/24/15.
 */
public abstract class DefaultAction extends AnAction {
    TomatoWidget widget;

    protected void init(Project p){
        if (widget == null){
            widget = (TomatoWidget) WindowManager.getInstance().getStatusBar(p).getWidget(ID);
        }
    }
}
