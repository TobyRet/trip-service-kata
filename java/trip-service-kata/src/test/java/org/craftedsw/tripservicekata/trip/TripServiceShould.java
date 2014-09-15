package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceShould {

    private static final User GUEST = null;
    private static final User SOME_USER = new User();
    private TripService tripService;
    private User loggedInUser;

    @Test(expected = UserNotLoggedInException.class) public void
    should_validate_the_logged_in_user() {
        this.tripService = new TestableTripService();

        loggedInUser = GUEST;

        tripService.getTripsByUser(SOME_USER);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User loggedInUser() {
            return loggedInUser;
        }
    }

}
