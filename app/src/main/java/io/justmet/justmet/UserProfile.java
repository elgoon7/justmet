package io.justmet.justmet;


import android.location.Location;

public class UserProfile {

    //Instance vars
    private String fullName;
    private String phoneNumber;
    private String company;
    private Location location; //???
    private String email;
    private String website;
    private String facebook;
    private String instagram;
    private String twitter;
    private String snapchat;
    private String linkedin;

    //Constructors
    public UserProfile() {   }

    public UserProfile(String fullName, String phoneNumber, String company, Location location, String email, String website, String facebook, String instagram, String twitter, String snapchat, String linkedin) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.location = location;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.snapchat = snapchat;
        this.linkedin = linkedin;
    }

    //Setters & Getters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSnapchat() {
        return snapchat;
    }
    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getLinkedin() {
        return linkedin;
    }
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
