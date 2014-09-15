package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static boolean isFriend = false;

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
		if (loggedInUser() == null ) {
            throw new UserNotLoggedInException();
        }
        if(loggedInUser() != null && areFriends(user, loggedInUser())) {
            tripList = findTrips(user);
		}
        return tripList;
	}

    protected boolean areFriends(User user, User loggedUser) {
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                isFriend = true;
                break;
            }
        }
        return isFriend;
    }

    protected List<Trip> findTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User loggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
