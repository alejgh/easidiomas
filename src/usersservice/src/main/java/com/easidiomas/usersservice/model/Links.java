package com.easidiomas.usersservice.model;

import java.util.Objects;

public class Links {

    private String self;
    private String first;
    private String prev;
    private String next;
    private String last;

    public Links() {
    }

    public Links(String self, String first, String prev, String next, String last) {
        this.self = self;
        this.first = first;
        this.prev = prev;
        this.next = next;
        this.last = last;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                ", first='" + first + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Links links = (Links) o;
        return self.equals(links.self) &&
                first.equals(links.first) &&
                prev.equals(links.prev) &&
                next.equals(links.next) &&
                last.equals(links.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, first, prev, next, last);
    }
}
