package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceTest {

    private static final User SOME_USER = new User() ;
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User BOB = new User() ;
    private static final Trip THAILAND = new Trip();
    private static final Trip USA = new Trip() ;
    private TripService tripService;
    private User loggedUser;

    @Before
    public void initialise() {
        tripService = new TestableTripService();
        loggedUser = REGISTERED_USER;
    }

    @Test(expected = UserNotLoggedInException.class) public void
    should_validate_loggedUser() {
        loggedUser = GUEST;
        tripService.getTripsByUser(SOME_USER);
    }

    @Test public void
    should_return_an_empty_friends_list_if_user_logged_in_but_is_a_stranger() {
        SOME_USER.addFriend(BOB);
        SOME_USER.addTrip(THAILAND);
        List<Trip> trips = tripService.getTripsByUser(SOME_USER);
        assertThat(trips.isEmpty(), is(true));
    }

    @Test public void
    should_return_populated_friends_list_if_user_is_logged_in_and_is_a_friend() {
        SOME_USER.addFriend(BOB);
        SOME_USER.addFriend(loggedUser);
        SOME_USER.addTrip(THAILAND);
        SOME_USER.addTrip(USA);
        List<Trip> trips = tripService.getTripsByUser(SOME_USER);
        System.out.println(trips);
        assertThat(trips.size(), is(2));
    }

    private class TestableTripService extends TripService{
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> tripListFor(User user) {
            return user.trips();
        }
     }


	
}
