package com.arimattitoivonen.questlog.domain;

import jakarta.persistence.*;

@Entity(name = "user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Enums.UserRole role; // ADMIN or USER

    public AppUser() {

    }

    public AppUser(String username, String passwordHash, Enums.UserRole role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Enums.UserRole getRole() {
        return role;
    }

    public void setRole(Enums.UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
                + "]";
    }

}
