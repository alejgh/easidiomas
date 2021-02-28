package com.easidiomas.auth.clients.usersservice;

import java.util.List;
import java.util.Objects;

public class ResultPageWrapper {

    private Links links;
    private int hits;
    private int total;
    private List<User> users;

    public ResultPageWrapper() {
    }

    public ResultPageWrapper(Links links, int hits, int total, List<User> users) {
        this.links = links;
        this.hits = hits;
        this.total = total;
        this.users = users;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ResultPageWrapper{" +
                "links=" + links +
                ", hits=" + hits +
                ", total=" + total +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultPageWrapper that = (ResultPageWrapper) o;
        return hits == that.hits &&
                total == that.total &&
                links.equals(that.links) &&
                users.equals(that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links, hits, total, users);
    }
}