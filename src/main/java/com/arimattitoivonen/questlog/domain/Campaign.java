package com.arimattitoivonen.questlog.domain;

public class Campaign {
    private Long id;
    private String name;
    private String description;
    private String game;
    private String user;
    private String status;

    public Campaign() {

    }

    public Campaign(String name, String description, String game, String user, String status) {
        this.name = name;
        this.description = description;
        this.game = game;
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Campaign [id=" + id + ", name=" + name + ", description=" + description + ", game=" + game + ", user="
                + user + ", status=" + status + "]";
    }

}
