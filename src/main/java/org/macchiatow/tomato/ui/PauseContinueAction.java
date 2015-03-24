package org.macchiatow.tomato.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

/**
 * Created by Togrul Mageramov on 23.03.2015.
 */
public class PauseContinueAction extends DefaultAction {

    public void actionPerformed(AnActionEvent e) {
        init(e.getProject());
        widget.pauseContinue();
    }

}
