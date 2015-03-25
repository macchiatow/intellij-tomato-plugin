package org.macchiatow.tomato.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import org.macchiatow.tomato.Initialization;

/**
 * Created by Togrul Mageramov on 25.03.2015.
 */
public class LoopAction extends DefaultAction {

    private boolean loop = false;

    @Override
    public void actionPerformed(AnActionEvent e) {
        if (loop){
            e.getPresentation().setIcon(null);
        } else {
            e.getPresentation().setIcon(IconLoader.getIcon("/actions/checked.png"));
        }

        loop = !loop;
        widget.setLoop(loop);
    }
}
