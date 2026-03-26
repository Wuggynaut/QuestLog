package com.arimattitoivonen.questlog.domain;

import java.time.LocalDate;

public class Session {
    private Long id;
    private Campaign campaign;
    private LocalDate date;
    private Integer duration;
    private String notes;
    private String role; // Player or GM

    public Session() {

    }

    public Session(Campaign campaign, LocalDate date, Integer duration, String notes, String role) {
        this.campaign = campaign;
        this.date = date;
        this.duration = duration;
        this.notes = notes;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Session [id=" + id + ", campaign=" + campaign + ", date=" + date + ", duration=" + duration + ", notes="
                + notes + ", role=" + role + "]";
    }

}
