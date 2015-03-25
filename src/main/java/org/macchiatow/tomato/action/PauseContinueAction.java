package org.macchiatow.tomato.action;

import com.google.common.base.Function;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.macchiatow.tomato.Initialization;

import javax.swing.*;

/**
 * Created by Togrul Mageramov on 23.03.2015.
 */
public class PauseContinueAction extends DefaultAction {
    Presentation presentation;

    public PauseContinueAction() {
        super.getTemplatePresentation().setEnabled(false);

        widget.addSubscriberOnActivate(new Function<Void, Void>() {
            @Override
            public Void apply(@Nullable Void o) {
                presentation.setEnabled(true);
                return null;
            }
        });
        widget.addSubscriberOnFinish(new Function<Void, Void>() {
            @Override
            public Void apply(@Nullable Void o) {
                presentation.setEnabled(false);
                return null;
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        presentation = e.getPresentation();
    }

    @Override
    public void actionPerformed(AnActionEvent e){;
        widget.pauseContinue();
    }

}
