package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;

import java.util.*;
import java.util.stream.Stream;

public class SpeaksLanguageFilter implements Filter<User> {

    private final Set<String> languageCodes;

    public SpeaksLanguageFilter(Set<String> languageCodes) {
        this.languageCodes = languageCodes;
    }

    @Override
    public Stream<User> filter(Stream<User> streamToFilter) {
        // If the filter does not need to be executed just return the previous list.
        if(Objects.isNull(languageCodes) || languageCodes.isEmpty()) {
            return streamToFilter;
        }

        Stream<User> filteredUsersStream =
                streamToFilter.filter(
                        user ->
                            languageCodes.contains(user.getSpeaks())
                        );
        return filteredUsersStream;
    }
}
