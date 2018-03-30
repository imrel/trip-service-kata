package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TripServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule()
            .strictness(Strictness.STRICT_STUBS);

    @Test
    public void should_throw_when_no_user_session_found() throws Exception {
        //given
        final TripService service = new TripService(){
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };

        //when/then
        assertThatExceptionOfType(UserNotLoggedInException.class).isThrownBy(() -> {
            service.getTripsByUser(null);
        });
    }
}