package com.arimattitoivonen.questlog.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String imageURL;
    private String yearPublished;

    public Game() {

    }

    public Game(String title, String description, String genre, String imageURL, String yearPublished) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.imageURL = imageURL;
        this.yearPublished = yearPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", description=" + description + ", genre=" + genre
                + ", imageURL=" + imageURL + ", yearPublished=" + yearPublished + "]";
    }

}
