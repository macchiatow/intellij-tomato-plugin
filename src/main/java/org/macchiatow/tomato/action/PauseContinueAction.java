/*
 * Copyright 2015 Togrul Mageramov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.macchiatow.tomato.action;

import com.google.common.base.Function;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Togrul Mageramov on 3/23/2015.
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
