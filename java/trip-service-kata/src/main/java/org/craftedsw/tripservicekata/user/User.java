package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Trip> trips = new ArrayList<Trip>();
    private final List<User> friends = new ArrayList<User>();

    public List<User> getFriends() {
        return this.friends;
    }

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public List<Trip> trips() {
        return this.trips;
    }

    public boolean isFriendsWith(User anotherUser) {
        return this.friends.contains(anotherUser);
    }
}
