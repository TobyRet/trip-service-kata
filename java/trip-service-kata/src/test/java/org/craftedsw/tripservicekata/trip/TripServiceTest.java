package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TripServiceTest {

    private static final User A_USER = new User() ;
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User A_FRIEND = new User() ;
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
        tripService.getTripsByUser(A_USER);
    }

    @Test public void
    should_not_return_any_trips_when_users_are_not_friends() {
        User stranger = new User();
        stranger.addFriend(A_FRIEND);
        stranger.addTrip(THAILAND);
        List<Trip> trips = tripService.getTripsByUser(stranger);
        assertThat(trips.isEmpty(), is(true));
    }

    @Test public void
    should_return_populated_friends_list_if_user_is_logged_in_and_is_a_friend() {
        User friend = new User();
        friend.addFriend(A_FRIEND);
        friend.addFriend(loggedUser);
        friend.addTrip(THAILAND);
        friend.addTrip(USA);
        List<Trip> trips = tripService.getTripsByUser(friend);
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
