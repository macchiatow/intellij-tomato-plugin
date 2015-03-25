package org.macchiatow.tomato.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import org.macchiatow.tomato.Initialization;
import org.macchiatow.tomato.ui.TomatoWidget;

import static org.macchiatow.tomato.Initialization.ID;

/**
 * Created by Togrul Mageramov on 3/24/15.
 */
public abstract class DefaultAction extends AnAction {
    TomatoWidget widget;

    public DefaultAction() {
        init(Initialization.PROJECT);
    }

    protected void init(Project p){
        if (widget == null){
            widget = (TomatoWidget) WindowManager.getInstance().getStatusBar(p).getWidget(ID);
        }
    }
}
