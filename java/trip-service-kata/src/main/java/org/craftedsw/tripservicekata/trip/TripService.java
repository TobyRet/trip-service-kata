package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        isLoggedIn();
        if(loggedInUser() != null && areFriends(user, loggedInUser())) {
            tripList = findTrips(user);
		}
        return tripList;
	}

    protected void isLoggedIn() {
        if (loggedInUser() == null ) {
            throw new UserNotLoggedInException();
        }
    }

    protected boolean areFriends(User user, User loggedInUser) {
          //return user.getFriends().contains(loggedInUser);
        return user.areYouFriendsWith(loggedInUser);
    }

    protected List<Trip> findTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User loggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
