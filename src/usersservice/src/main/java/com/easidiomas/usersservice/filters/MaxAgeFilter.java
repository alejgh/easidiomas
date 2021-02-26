package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;

import java.util.*;
import java.util.stream.Stream;

public class MaxAgeFilter implements Filter<User> {

    private final Integer maxAgeFilterValue;

    public MaxAgeFilter(Integer maxAgeFilterValue) {
        this.maxAgeFilterValue = maxAgeFilterValue;
    }

    @Override
    public Stream<User> filter(Stream<User> streamToFilter) {
        // If the filter does not need to be executed just return the previous list.
        if(Objects.isNull(maxAgeFilterValue) || maxAgeFilterValue.intValue() < 0) {
            return streamToFilter;
        }

        // Otherwise start the filter.
        Calendar userBirthDate = new GregorianCalendar();
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        Stream<User> filteredUsersStream =
                streamToFilter.filter(
                        user -> {
                            userBirthDate.setTime(user.getBirthDate());
                            int currentYear = today.get(Calendar.YEAR);
                            int userBirthYear = today.get(Calendar.YEAR);
                            int userAgeInYears = currentYear - userBirthYear;
                            return userAgeInYears <= maxAgeFilterValue;
                        }
                );
        return filteredUsersStream;
    }
}
