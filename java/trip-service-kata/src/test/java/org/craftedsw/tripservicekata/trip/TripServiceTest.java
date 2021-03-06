package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private static final User SOME_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip THAILAND = new Trip();
    private static final Trip ICELAND = new Trip() ;
    private TripService realTripService;
    private TripDAO tripDAO;
    private User loggedInUser;
    private User GUEST = null;
    private User REGISTERED_USER = new User();

    @Before
    public void initialise() {
        loggedInUser = REGISTERED_USER;
        tripDAO = mock(TripDAO.class);
        realTripService = new TripService(tripDAO);
    }

    @Test(expected = UserNotLoggedInException.class) public void
    validates_the_logged_in_user() {
        loggedInUser = GUEST;
        realTripService.getTripsByUser(SOME_USER, GUEST );
    }

    @Test public void
    return_an_empty_list_if_user_logged_in_and_not_a_friend() {

        User stranger = UserBuilder.aUser()
                            .friendsWith(ANOTHER_USER)
                            .withTripsTo(THAILAND)
                            .build();

        List<Trip> trips = realTripService.getTripsByUser(stranger, REGISTERED_USER);
        assertThat(trips.isEmpty(), is(true));
    }

    @Test public void
    return_a_populated_trips_list_if_user_is_logged_in_and_a_friend() {
        User friend = UserBuilder.aUser()
                            .friendsWith(ANOTHER_USER, REGISTERED_USER)
                            .withTripsTo(THAILAND, ICELAND)
                            .build();

        when(tripDAO.tripsBy(friend)).thenReturn(friend.trips());

        List<Trip> trips = realTripService.getTripsByUser(friend, REGISTERED_USER);
        assertThat(trips.isEmpty(), is(false));
        assertThat(trips.size(), is(2));
    }

}
