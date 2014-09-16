package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

    private static final User SOME_USER = new User();
    private TripService tripService;
    private User loggedInUser;
    private User GUEST = null;

    @Test(expected = UserNotLoggedInException.class) public void
    validates_the_logged_in_user() {
        loggedInUser = GUEST;
        tripService = new TestableTripService();
        tripService.getTripsByUser(SOME_USER);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User loggedInUser() {
            return loggedInUser;
        }
    }
	
}
