package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final Trip TO_TALLINN = new Trip();

    @Rule
    public MockitoRule rule = MockitoJUnit.rule()
            .strictness(Strictness.STRICT_STUBS);

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    private final TripService service = new TripService();

    private final User user = new User();

    @Test
    public void should_throw_when_no_user_session_found() throws Exception {
        //given

        //when/then
        assertThatExceptionOfType(UserNotLoggedInException.class).isThrownBy(
                () -> this.service.getFriendTrips(null, GUEST));
    }

    @Test
    public void no_trips_when_user_has_no_friends() throws Exception {
        //given
        final User lonelyGuy = new User();

        //when
        final List<Trip> trips = this.service.getFriendTrips(lonelyGuy, REGISTERED_USER);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void no_trips_when_user_has_friends_but_not_friend_with_logged_user() throws Exception {
        //given
        final User notFriend = new UserBuilder()
                .withFriends(new User(), new User())
                .withTrips(TO_TALLINN)
                .build();

        //when
        final List<Trip> trips = this.service.getFriendTrips(notFriend, REGISTERED_USER);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void should_return_friend_trips() throws Exception {
        //given
        final User friend = new UserBuilder()
                .withFriends(new User(), REGISTERED_USER)
                .withTrips(TO_TALLINN)
                .build();

        given(this.tripDAO.findByUser(friend)).willReturn(friend.trips());

        //when
        final List<Trip> trips = this.service.getFriendTrips(friend, REGISTERED_USER);

        //then
        assertThat(trips).isEqualTo(friend.trips());
    }
}
