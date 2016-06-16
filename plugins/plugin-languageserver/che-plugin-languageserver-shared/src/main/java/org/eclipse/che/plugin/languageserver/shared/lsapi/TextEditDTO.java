/**
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.che.plugin.languageserver.shared.lsapi;

import org.eclipse.che.dto.shared.DTO;

import io.typefox.lsapi.TextEdit;

@DTO
public interface TextEditDTO extends TextEdit {
    /**
     * The range of the text document to be manipulated. To insert text into a
     * document create a range where start === end. Overridden to return the DTO
     * type.
     * 
     */
    public abstract RangeDTO getRange();

    /**
     * The range of the text document to be manipulated. To insert text into a
     * document create a range where start === end.
     * 
     */
    public abstract void setRange(final RangeDTO range);

    /**
     * The string to be inserted. For delete operations use an empty string.
     * 
     */
    public abstract void setNewText(final String newText);
}
