package com.easidiomas.statisticsservice.model;


import java.util.Map;

public class SystemStatistics {

    private RegisteredUsersData registeredUsers;
    private Map<String, Integer> createdPosts;
    private int createdChats;

    public SystemStatistics() {
    }

    public SystemStatistics(RegisteredUsersData registeredUsers, Map<String, Integer> createdPosts, int createdChats) {
        this.registeredUsers = registeredUsers;
        this.createdPosts = createdPosts;
        this.createdChats = createdChats;
    }

    public RegisteredUsersData getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(RegisteredUsersData registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Map<String, Integer> getCreatedPosts() {
        return createdPosts;
    }

    public void setCreatedPosts(Map<String, Integer> createdPosts) {
        this.createdPosts = createdPosts;
    }

    public int getCreatedChats() {
        return createdChats;
    }

    public void setCreatedChats(int createdChats) {
        this.createdChats = createdChats;
    }

    @Override
    public String toString() {
        return "SystemStatistics{" +
                "registeredUsers=" + registeredUsers +
                ", createdPosts=" + createdPosts +
                ", createdChats=" + createdChats +
                '}';
    }

    public static class RegisteredUsersData {
        private int total;
        private Map<String, Integer> learning;
        private Map<String, Integer> nnative;

        public RegisteredUsersData() {
        }

        public RegisteredUsersData(int total, Map<String, Integer> learning, Map<String, Integer> nnative) {
            this.total = total;
            this.learning = learning;
            this.nnative = nnative;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public Map<String, Integer> getLearning() {
            return learning;
        }

        public void setLearning(Map<String, Integer> learning) {
            this.learning = learning;
        }

        public Map<String, Integer> getNnative() {
            return nnative;
        }

        public void setNnative(Map<String, Integer> nnative) {
            this.nnative = nnative;
        }

        @Override
        public String toString() {
            return "RegisteredUsersData{" +
                    "total=" + total +
                    ", learning=" + learning +
                    ", nnative=" + nnative +
                    '}';
        }
    }
}
