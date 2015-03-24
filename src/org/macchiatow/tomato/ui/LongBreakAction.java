package org.macchiatow.tomato.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

/**
 * Created by tmageramov on 23.03.2015.
 */
public class LongBreakAction extends AnAction {
    TomatoWidget widget;

    public void actionPerformed(AnActionEvent e) {
        init(e.getProject());
        widget.longBreak();
    }

    private void init(Project p){
        if (widget == null){
            widget = (TomatoWidget) WindowManager.getInstance().getStatusBar(p).getWidget(Initialization.id);
        }
    }
}
