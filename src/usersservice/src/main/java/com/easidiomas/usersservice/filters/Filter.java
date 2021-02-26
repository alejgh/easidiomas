package com.easidiomas.usersservice.filters;


import java.util.stream.Stream;

public interface Filter<T> {

    Stream<T> filter(Stream<T> streamToFilter);
}
