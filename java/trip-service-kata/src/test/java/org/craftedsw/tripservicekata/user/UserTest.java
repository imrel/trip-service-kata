package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void should_check_if_isFriendsWith() throws Exception {
        //given
        final User friend = new User();
        final User stranger = new User();

        final User user = new UserBuilder()
                .withFriends(friend)
                .build();

        //when
        final boolean friendResult = user.isFriendsWith(friend);
        final boolean strangerResult = user.isFriendsWith(stranger);

        //then
        assertThat(friendResult).isTrue();
        assertThat(strangerResult).isFalse();
    }
}
