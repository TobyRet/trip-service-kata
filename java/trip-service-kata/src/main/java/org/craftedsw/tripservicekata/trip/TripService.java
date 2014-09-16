package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
		return user.isFriendsWith(loggedInUser)
                ? tripsByUser(user)
                : new ArrayList<Trip>();
	}

    private List<Trip> tripsByUser(User user) {
        return tripDAO.tripsBy(user);
    }

}
