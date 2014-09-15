package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceShould {

    private static final User GUEST = null;
    private static final User SOME_USER = new User();
    private static final User USER_WITH_ACCOUNT = new User();
    private static final Trip TRIP1 = new Trip();
    private static final Trip TRIP2 = new Trip();
    private TripService tripService;
    private User loggedInUser;
    private ArrayList<Trip> someUserTripList;
    private User someMockedUser;
    private ArrayList<User> friendList;

    @Before
    public void initialise() {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class) public void
    should_validate_the_logged_in_user() {

        loggedInUser = GUEST;

        tripService.getTripsByUser(SOME_USER);
    }

    @Test public void
    return_empty_tripList_if_logged_in_user_is_not_friends_with_another_user() {
        loggedInUser = USER_WITH_ACCOUNT;

        assertThat(tripService.getTripsByUser(SOME_USER).isEmpty(), is(true));
    }

    @Test public void
    return_tripList_if_logged_in_user_is_friends_with_another_user() {
        loggedInUser = USER_WITH_ACCOUNT;

        SOME_USER.addFriend(loggedInUser);
        SOME_USER.addTrip(TRIP1);

        assertThat(tripService.getTripsByUser(SOME_USER).isEmpty(), is(false));
    }

    private class TestableTripService extends TripService {
        @Override
        protected User loggedInUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTrips(User user) {
            return SOME_USER.trips();
        }
    }

}
