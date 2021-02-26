package com.easidiomas.usersservice.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Node
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    // Internal application fields.
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    // User public fields.
    private String name;
    private String surname;
    private String[] learning;
    private String speaks;
    private Date birthDate;
    private String avatarUrl;
    //@Relationship(type = "IS_FOLLOWING")
    //private Set<User> follows;

    public User() {}

    public User(String username, String password, String name, String surname, String[] learning, String speaks, Date birthDate, String avatarUrl) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.learning = learning;
        this.speaks = speaks;
        this.birthDate = birthDate;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    //public Set<User> getFollows() {
    //    return follows;
    //}

    //public void setFollows(Set<User> follows) {
    //    this.follows = follows;
    //}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", learning=" + learning +
                ", speaks=" + speaks +
                ", birthDate=" + birthDate +
                ", avatarUrl='" + avatarUrl + '\'' +
                //", follows=" + follows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
