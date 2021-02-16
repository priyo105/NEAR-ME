package com.example.nearme.ui.Acitivities.objects;

public class ReviewObject {
    private int id;
    private String AuthorName;
    private String profilePhoto;
    private Double rating;
    private String comment;
    private String timeDescuription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeDescuription() {
        return timeDescuription;
    }

    public void setTimeDescuription(String timeDescuription) {
        this.timeDescuription = timeDescuription;
    }
}
