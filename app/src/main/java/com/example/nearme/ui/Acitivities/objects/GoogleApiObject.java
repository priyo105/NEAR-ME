package com.example.nearme.ui.Acitivities.objects;

public class GoogleApiObject {
    private Double lat;
    private Double lon;
    private String name;
    private String photoReference;
    private String placeId;
    private String reference;
    private String vicinity;

    public GoogleApiObject(Double lat, Double lon, String name, String photoReference, String placeId, String reference, String vicinity) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.photoReference = photoReference;
        this.placeId = placeId;
        this.reference = reference;
        this.vicinity = vicinity;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
