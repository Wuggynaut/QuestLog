package com.arimattitoivonen.questlog.domain;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private LocalDate date;
    private Integer duration;
    private String notes;
    @Enumerated(EnumType.STRING)
    private Enums.SessionRole role;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;// Player or GM

    public Session() {

    }

    public Session(Campaign campaign, Game game, LocalDate date, Integer duration, String notes, Enums.SessionRole role, AppUser user) {
        this.campaign = campaign;
        this.game = game;
        this.date = date;
        this.duration = duration;
        this.notes = notes;
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Enums.SessionRole getRole() {
        return role;
    }

    public void setRole(Enums.SessionRole role) {
        this.role = role;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", campaign=" + campaign +
                ", game=" + game +
                ", date=" + date +
                ", duration=" + duration +
                ", notes='" + notes + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
