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
package org.eclipse.che.api.user.server.spi;

import org.eclipse.che.api.core.ConflictException;
import org.eclipse.che.api.core.NotFoundException;
import org.eclipse.che.api.core.ServerException;
import org.eclipse.che.api.core.UnauthorizedException;
import org.eclipse.che.api.user.server.model.impl.UserImpl;

/**
 * Defines data access object contract for {@link UserImpl}.
 *
 * <p>The implementation is not required to be responsible for persistent layer
 * data dto integrity. It simply transfers data from one layer to another,
 * so if you're going to call any of implemented methods it is considered
 * that all needed verifications are already done.
 *
 * <p><strong>Note:</strong> This particularly does not mean that
 * method call will not make any inconsistency, but this mean that
 * such kind of inconsistencies are expected by design and may be treated further.
 *
 * @author Yevhenii Voevodin
 */
public interface UserDao {

    /**
     * // TODO remove this method from spi
     * Authenticates user.
     *
     * @param emailOrAliasOrName
     *         one of the user identifiers such as email/name/alias
     * @param password
     *         password
     * @return user identifier
     * @throws NullPointerException
     *         when either {@code emailOrAliasOrName} or {@code password} is null
     * @throws UnauthorizedException
     *         when user with such {@code aliasOrName} and {@code password} doesn't exist
     * @throws ServerException
     *         when any other error occurs
     */
    String authenticate(String emailOrAliasOrName, String password) throws UnauthorizedException, ServerException;

    /**
     * Creates a new user.
     *
     * @param user
     *         user to create
     * @throws NullPointerException
     *         when {@code user} is null
     * @throws ConflictException
     *         when user with such id/alias/email/name already exists
     * @throws ServerException
     *         when any other error occurs
     */
    void create(UserImpl user) throws ConflictException, ServerException;

    /**
     * Updates user by replacing an existing entity with a new one.
     *
     * @param user
     *         user to update
     * @throws NullPointerException
     *         when {@code user} is null
     * @throws NotFoundException
     *         when user with id {@code user.getId()} doesn't exist
     * @throws ConflictException
     *         when any of the id/alias/email/name updated with a value
     *         which is not unique
     * @throws ServerException
     *         when any other error occurs
     */
    void update(UserImpl user) throws NotFoundException, ServerException, ConflictException;

    /**
     * Removes user.
     *
     * <p>It is up to implementation to do cascade removing of dependent data or
     * to forbid removing at all.
     *
     * <p>Note that this method doesn't throw any exception when
     * user doesn't exist.
     *
     * @param id
     *         user identifier
     * @throws NullPointerException
     *         when {@code id} is null
     * @throws ConflictException
     *         when given user cannot be deleted
     * @throws ServerException
     *         when any other error occurs
     */
    void remove(String id) throws ServerException, ConflictException;

    /**
     * Finds user by his alias.
     *
     * <p>This method doesn't work for user's email or name.
     * If it is necessary to get user by name use {@link #getByName(String)} method instead.
     *
     * @param alias
     *         user name or alias
     * @return user instance, never null
     * @throws NullPointerException
     *         when {@code alias} is null
     * @throws NotFoundException
     *         when user with given {@code alias} doesn't exist
     * @throws ServerException
     *         when any other error occurs
     */
    UserImpl getByAlias(String alias) throws NotFoundException, ServerException;

    /**
     * Finds user by his identifier.
     *
     * @param id
     *         user identifier
     * @return user instance, never null
     * @throws NullPointerException
     *         when {@code id} is null
     * @throws NotFoundException
     *         when user with given {@code id} doesn't exist
     * @throws ServerException
     *         when any other error occurs
     */
    UserImpl getById(String id) throws NotFoundException, ServerException;

    /**
     * Finds user by his name.
     *
     * @param name
     *         user name
     * @return user instance, never null
     * @throws NullPointerException
     *         when {@code name} is null
     * @throws NotFoundException
     *         when user with such {@code name} doesn't exist
     * @throws ServerException
     *         when any other error occurs
     */
    UserImpl getByName(String name) throws NotFoundException, ServerException;

    /**
     * Finds user by his email.
     *
     * @param email
     *         user email
     * @return user instance, never null
     * @throws NullPointerException
     *         when {@code email} is null
     * @throws NotFoundException
     *         when user with such {@code email} doesn't exist
     * @throws ServerException
     *         when any other error occurs
     */
    UserImpl getByEmail(String email) throws NotFoundException, ServerException;
}
