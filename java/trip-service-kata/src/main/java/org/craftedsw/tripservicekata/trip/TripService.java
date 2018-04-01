package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class TripService {

    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    @Inject
    private TripDAO tripDAO;

    public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        if (friend.isFriendsWith(loggedInUser)) {
            return this.findFriendTrips(friend);
        }

        return NO_TRIPS;
    }

    protected List<Trip> findFriendTrips(User user) {
        return this.tripDAO.findByUser(user);
    }
}
