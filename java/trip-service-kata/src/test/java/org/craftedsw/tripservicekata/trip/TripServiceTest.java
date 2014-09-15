package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripService tripService;
    private User user;
    private TripDAO tripList;
    private UserSession mockedUserSession;


    /* @Ignore
    @Test(expected = UserNotLoggedInException.class) public void
    throws_exception_if_user_not_logged_in() {
        user = mock(User.class);
        tripService = new TripService();
        tripService.getTripsByUser(user);
    }

    @Test public void
    return_a_list_containing_a_users_trips_if_loggedUser_is_a_friend() {
        tripList = mock(TripDAO.class);
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip());
        when(tripList.findTripsByUser(user)).thenReturn(trips);

    }

    @Test public void
    check_to_see_if_user_is_logged_in() {
        mockedUserSession = mock(UserSession.class);
        tripService = new TripService();
        when(mockedUserSession.inSession()).thenReturn(true);
        tripService.checkUserLoggedIn */

    @Test(expected = RuntimeException.class) public void
    throw_exception_if_user_not_logged_in() {
        user = mock(User.class);
        when(user.loggedIn()).thenReturn(false);
        tripService.getTripsByUser(user);
    }
	
}
