package org.macchiatow.tomato.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by Togrul Mageramov on 25.03.2015.
 */
public class ResetAction extends DefaultAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        init(e.getProject());
        widget.setPomodoro(Integer.valueOf(e.getPresentation().getText()));
    }

}
