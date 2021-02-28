package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;

import java.util.stream.Stream;

public class UsernameFilter implements Filter<User> {

    private String username;

    public UsernameFilter(String username) {
        this.username = username;
    }

    @Override
    public Stream<User> filter(Stream<User> streamToFilter) {
        if(username == null || username.isEmpty()) {
            return streamToFilter;
        }
        Stream<User> filteredUsersStream =
                streamToFilter.filter(
                        user -> user.getUsername().toLowerCase().equals(this.username.toLowerCase())
                );
        return filteredUsersStream;
    }
}
