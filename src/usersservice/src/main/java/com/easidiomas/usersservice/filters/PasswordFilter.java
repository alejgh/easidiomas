package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;


import java.util.stream.Stream;

public class PasswordFilter implements Filter<User> {

    private String password;

    public PasswordFilter(String password) {
        this.password = password;
    }

    @Override
    public Stream<User> filter(Stream<User> streamToFilter) {
        if(password == null || password.isEmpty()) {
            return streamToFilter;
        }
        Stream<User> filteredUsersStream =
                streamToFilter.filter(
                        user -> user.getPassword().toLowerCase().equals(this.password.toLowerCase())
                );
        return filteredUsersStream;
    }
}
