package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
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
    final TripService service = new TripService();

    private final User user = new User();

    @Test
    public void should_throw_when_no_user_session_found() throws Exception {
        //given

        //when/then
        assertThatExceptionOfType(UserNotLoggedInException.class).isThrownBy(
                () -> this.service.getTripsByUser(this.user, GUEST));
    }

    @Test
    public void no_trips_when_user_has_no_friends() throws Exception {
        //given

        //when
        final List<Trip> trips = this.service.getTripsByUser(this.user, REGISTERED_USER);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void no_trips_when_user_has_friends_but_not_friend_with_logged_user() throws Exception {
        //given
        this.user.addFriend(new User());
        this.user.addFriend(new User());
        this.user.addTrip(TO_TALLINN);

        //when
        final List<Trip> trips = this.service.getTripsByUser(this.user, REGISTERED_USER);

        //then
        assertThat(trips).isEmpty();
    }

    @Test
    public void should_return_user_trips() throws Exception {
        //given
        this.user.addFriend(REGISTERED_USER);
        this.user.addTrip(TO_TALLINN);

        given(this.tripDAO.findByUser(this.user)).willReturn(this.user.trips());

        //when
        final List<Trip> trips = this.service.getTripsByUser(this.user, REGISTERED_USER);

        //then
        assertThat(trips).isEqualTo(this.user.trips());
    }
}
