package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

public class TripServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule()
            .strictness(Strictness.STRICT_STUBS);

    @Test
    public void should_throw_when_no_user_session_found() throws Exception {
        //given
        final TripService service = new TripService() {
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };

        //when/then
        assertThatExceptionOfType(UserNotLoggedInException.class).isThrownBy(() -> service.getTripsByUser(null));
    }

    @Test
    public void no_trips_when_user_has_no_friends() throws Exception {
        //given
        final User loggedUser = new User();
        final User user = new User();

        final TripService service = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };


        //when
        final List<Trip> trips = service.getTripsByUser(user);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void no_trips_when_user_has_friends_but_not_friend_with_logged_user() throws Exception {
        //given
        final User loggedUser = new User();
        final User user = new User();
        user.addFriend(new User());
        user.addFriend(new User());

        final TripService service = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };


        //when
        final List<Trip> trips = service.getTripsByUser(user);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void should_return_user_trips() throws Exception {
        //given
        final User loggedUser = new User();
        final User user = new User();
        user.addFriend(loggedUser);

        final List<Trip> tripList = new ArrayList<>();
        tripList.add(mock(Trip.class));

        final TripService service = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> findFriendTrips(User user) {
                return tripList;
            }
        };

        //when
        final List<Trip> trips = service.getTripsByUser(user);

        //then
        assertThat(trips).isEqualTo(tripList);
    }
}
