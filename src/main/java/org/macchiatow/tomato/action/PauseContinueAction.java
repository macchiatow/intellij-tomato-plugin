package org.macchiatow.tomato.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.Initialization;

import javax.swing.*;

/**
 * Created by Togrul Mageramov on 23.03.2015.
 */
public class PauseContinueAction extends DefaultAction {
    Presentation presentation;

    public PauseContinueAction() {
        super();
        presentation = super.getTemplatePresentation();
        presentation.setEnabled(false);
    }

    @Override
    public void actionPerformed(AnActionEvent e){;
        widget.pauseContinue();
    }

}
