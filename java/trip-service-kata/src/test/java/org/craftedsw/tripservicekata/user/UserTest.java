package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.craftedsw.tripservicekata.trip.UserBuilder.aUser;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private static final User PAUL = new User();
    private static final User TOBY = new User();

    @Test public void
    should_inform_when_users_are_not_friends() {
        User user = aUser().friendsWith(PAUL).build();
        assertThat(user.isFriendsWith(TOBY), is(false));

    }

    @Test public void
    should_inform_when_users_are_friends() {
        User user = aUser().friendsWith(PAUL, TOBY).build();
        assertThat(user.isFriendsWith(TOBY), is(true));
    }

}
