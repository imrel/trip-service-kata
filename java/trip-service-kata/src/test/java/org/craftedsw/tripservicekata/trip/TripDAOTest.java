package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SuppressWarnings("unused")
public class TripDAOTest {

    @Test
    public void should_throw_when_getting_user_trips() throws Exception {
        //given

        //when/then
        assertThatExceptionOfType(CollaboratorCallException.class).isThrownBy(() -> new TripDAO().findTripsBy(new User()));
    }
}
