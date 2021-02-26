package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class WantsToLearnLanguageFilter implements Filter<User> {

    private final Set<String> languageCodes;

    public WantsToLearnLanguageFilter(Set<String> languageCodes) {
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
                        user -> {
                            for(String lang : user.getLearning()) {
                                if(languageCodes.contains(lang)) {
                                    return true;
                                }
                            }
                            return false;
                        }
                );
        return filteredUsersStream;
    }
}
