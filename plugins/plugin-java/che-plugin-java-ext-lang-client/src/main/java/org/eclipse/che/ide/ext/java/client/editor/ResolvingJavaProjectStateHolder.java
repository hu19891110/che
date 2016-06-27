/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.ext.java.client.editor;

import com.google.gwt.user.client.Timer;

import org.eclipse.che.ide.project.ResolvingProjectStateHolder;

import javax.inject.Singleton;

import java.util.HashSet;

import static org.eclipse.che.ide.project.ResolvingProjectStateHolder.ResolvingProjectState.NOT_RESOLVED;

@Singleton
public class ResolvingJavaProjectStateHolder implements ResolvingProjectStateHolder {
    private ResolvingProjectState state;
    private HashSet<ResolvingProjectStateListener> listeners;

    public ResolvingJavaProjectStateHolder() {
        this.state = NOT_RESOLVED;
        this.listeners = new HashSet<>();
    }

    @Override
    public ResolvingProjectState getState() {
        return state;
    }

    @Override
    public void setState(ResolvingProjectState state) {
        this.state = state;
        notifyListenersTimer.cancel();
        notifyListenersTimer.schedule(200);
    }

    @Override
    public void addResolvingProjectStateListener(ResolvingProjectStateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeResolvingProjectStateListener(ResolvingProjectStateListener listener) {
        listeners.remove(listener);
    }

    /** We need to have some delay to avoid a flashing when a resolving project state has been changed */
    private Timer notifyListenersTimer = new Timer() {
        @Override
        public void run() {
            for (ResolvingProjectStateListener listener : listeners) {
                listener.onResolvingProjectStateChanged(state);
            }
        }
    };
}
