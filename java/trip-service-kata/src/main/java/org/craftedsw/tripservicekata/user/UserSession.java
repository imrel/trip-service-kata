package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public class UserSession {

    private static final UserSession USER_SESSION = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return USER_SESSION;
    }

    public User getLoggedUser() {
        throw new CollaboratorCallException("UserSession.getLoggedUser() should not be called in an unit test");
    }
}
