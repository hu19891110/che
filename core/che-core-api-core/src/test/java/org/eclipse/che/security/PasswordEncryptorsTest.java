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
package org.eclipse.che.security;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TODO add tests
 *
 * @author Yevhenii Voevodin
 */
public class PasswordEncryptorsTest {

    @Test
    public void testEncryption(PasswordEncryptor encryptor) throws Exception {

    }

    @DataProvider(name = "encryptorsProvider")
    public Object[][] encryptorsProvider() {
        return new Object[][] {
                { new SHA512PasswordEncryptor() }
        };
    }
}