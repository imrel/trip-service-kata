package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static final ArrayList<Trip> NO_TRIPS = new ArrayList<>();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        final User loggedUser = this.getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (user.isFriendsWith(loggedUser)) {
            return this.findFriendTrips(user);
        }

        return NO_TRIPS;
    }

    protected List<Trip> findFriendTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance()
                .getLoggedUser();
    }
}
