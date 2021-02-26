package com.easidiomas.usersservice.filters;

import com.easidiomas.usersservice.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pipeline {

    private final List<Filter> filters;
    private final Collection<User> collectionToFilter;

    private List<User> filteredResultList;

    public Pipeline(Collection<User> collectionToFilter) {
        this.filters = new ArrayList<Filter>();
        this.collectionToFilter = collectionToFilter;
    }

    public void registerFilter(Filter filter) {
        this.filters.add(filter);
    }

    public List<User> executePipelineAndGetResult() {
        if(Objects.isNull(filteredResultList)) {
            Stream<User> filteredStream = this.collectionToFilter.parallelStream();
            for(Filter filter: filters) {
                filteredStream = filter.filter(filteredStream);
            }
            this.filteredResultList = filteredStream.collect(Collectors.toList());
            return this.filteredResultList;
        }
        return this.filteredResultList;
    }
}
