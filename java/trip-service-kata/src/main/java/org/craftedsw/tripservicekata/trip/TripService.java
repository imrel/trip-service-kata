package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

public class TripService {

    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        if (user.isFriendsWith(loggedInUser)) {
            return this.findFriendTrips(user);
        }

        return NO_TRIPS;
    }

    protected List<Trip> findFriendTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
