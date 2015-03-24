package org.macchiatow.tomato.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

/**
 * Created by tmageramov on 23.03.2015.
 */
public class LongBreakAction extends DefaultAction {
    TomatoWidget widget;

    public void actionPerformed(AnActionEvent e) {
        init(e.getProject());
        widget.longBreak();
    }


}
