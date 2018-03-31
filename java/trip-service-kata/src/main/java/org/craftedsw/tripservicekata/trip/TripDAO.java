package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

@SuppressWarnings("unused")
public class TripDAO {

    public static List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException("TripDAO should not be invoked on an unit test.");
    }

    public List<Trip> findTripsBy(User user) {

        return findTripsByUser(user);
    }
}
