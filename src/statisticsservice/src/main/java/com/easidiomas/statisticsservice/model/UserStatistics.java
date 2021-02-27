package com.easidiomas.statisticsservice.model;

import java.util.Map;
import java.util.Objects;

public class UserStatistics {

    private String userId;
    private int translationsMade;
    private int textToSpeechMade;
    private Map<String, Integer> createdPosts;

    public UserStatistics(String userId, int translationsMade, int textToSpeechMade, Map<String, Integer> createdPosts) {
        this.userId = userId;
        this.translationsMade = translationsMade;
        this.textToSpeechMade = textToSpeechMade;
        this.createdPosts = createdPosts;
    }

    public UserStatistics() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTranslationsMade() {
        return translationsMade;
    }

    public void setTranslationsMade(int translationsMade) {
        this.translationsMade = translationsMade;
    }

    public int getTextToSpeechMade() {
        return textToSpeechMade;
    }

    public void setTextToSpeechMade(int textToSpeechMade) {
        this.textToSpeechMade = textToSpeechMade;
    }

    public Map<String, Integer> getCreatedPosts() {
        return createdPosts;
    }

    public void setCreatedPosts(Map<String, Integer> createdPosts) {
        this.createdPosts = createdPosts;
    }

    @Override
    public String toString() {
        return "UserStatistics{" +
                "userId='" + userId + '\'' +
                ", translationsMade=" + translationsMade +
                ", textToSpeechMade=" + textToSpeechMade +
                ", createdPosts=" + createdPosts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatistics that = (UserStatistics) o;
        return translationsMade == that.translationsMade &&
                textToSpeechMade == that.textToSpeechMade &&
                userId.equals(that.userId) &&
                createdPosts.equals(that.createdPosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, translationsMade, textToSpeechMade, createdPosts);
    }
}
