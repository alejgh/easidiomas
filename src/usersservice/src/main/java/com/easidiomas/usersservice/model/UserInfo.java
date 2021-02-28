package com.easidiomas.usersservice.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class UserInfo {
    private String username;
    private String password;

    private String name;
    private String surname;
    private String[] learning;
    private String speaks;
    private Long birthDate;
    private String base64Image;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String name, String surname, String[] learning, String speaks, Long birthDate, String base64Image) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.learning = learning;
        this.speaks = speaks;
        this.birthDate = birthDate;
        this.base64Image = base64Image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String[] getLearning() {
        return learning;
    }

    public void setLearning(String[] learning) {
        this.learning = learning;
    }

    public String getSpeaks() {
        return speaks;
    }

    public void setSpeaks(String speaks) {
        this.speaks = speaks;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", learning=" + learning +
                ", speaks=" + speaks +
                ", birthDate=" + birthDate +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return username.equals(userInfo.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
