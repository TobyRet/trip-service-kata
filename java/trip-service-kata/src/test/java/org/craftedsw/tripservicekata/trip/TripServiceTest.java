package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceTest {

    private static final User SOME_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip THAILAND = new Trip();
    private TestableTripService testableTripService;
    private User loggedInUser;
    private User GUEST = null;
    private User REGISTERED_USER = new User();

    @Before
    public void initialise() {
        testableTripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class) public void
    validates_the_logged_in_user() {
        loggedInUser = GUEST;
        testableTripService.getTripsByUser(SOME_USER);
    }

    @Test public void
    return_an_empty_list_if_user_logged_in_and_not_a_friend() {
        loggedInUser = REGISTERED_USER;

        User stranger = new User();
        stranger.addFriend(ANOTHER_USER);
        stranger.addTrip(THAILAND);

        List<Trip> trips = testableTripService.getTripsByUser(stranger);
        assertThat(trips.isEmpty(), is(true));
    }

    private class TestableTripService extends TripService {
        @Override
        protected User loggedInUser() {
            return loggedInUser;
        }
    }
	
}
