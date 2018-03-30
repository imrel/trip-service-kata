package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void should_check_if_isFriendsWith() throws Exception {
        //given
        final User user = new User();
        final User friend = new User();
        user.addFriend(friend);
        final User stranger = new User();

        //when
        final boolean friendResult = user.isFriendsWith(friend);
        final boolean strangerResult = user.isFriendsWith(stranger);

        //then
        assertThat(friendResult).isTrue();
        assertThat(strangerResult).isFalse();
    }
}
