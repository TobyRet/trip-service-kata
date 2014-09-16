package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (loggedInUser() == null) {
            throw new UserNotLoggedInException();
        }
		return user.isFriendsWith(loggedInUser())
                ? tripsByUser(user)
                : new ArrayList<Trip>();
	}

    protected List<Trip> tripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User loggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
