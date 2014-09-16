package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

/**
* Created by tobyretallick on 16/09/2014.
*/
public class UserBuilder {

    private User[] friends = new User[] {};
    private Trip[] trips = new Trip[] {};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }


    public UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTripsTo(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addTripsTo(user);
        addFriendsTo(user);
        return user;
    }

    private void addFriendsTo(User user) {
        for(User friend : friends) {
            user.addFriend(friend);
        }
    }

    private void addTripsTo(User user) {
        for(Trip trip : trips) {
            user.addTrip(trip);
        }
    }
}
