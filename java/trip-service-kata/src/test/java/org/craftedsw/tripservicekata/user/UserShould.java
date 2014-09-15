package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class UserShould {

    private static final User bob = new User();
    private static final User toby = new User();
    private static final User rivka = new User();

    @Test public void
    return_a_list_of_friends_associated_with_a_user() {
        bob.addFriend(toby);
        bob.addFriend(rivka);
        assertThat(bob.getFriends(), hasItems(toby, rivka));
    }

    @Test public void
    know_if_another_user_is_a_friend() {
        bob.addFriend(toby);
        assertTrue(bob.areYouFriendsWith(toby));
    }


}