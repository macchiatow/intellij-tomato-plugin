package org.macchiatow.tomato.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

/**
 * Created by Togrul Mageramov on 23.03.2015.
 */
public class ShortBreakAction extends DefaultAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        widget.shortBreak();
    }
}
