package com.coremedia.labs.contenthub.adapters.unsplash.service.users;

import com.coremedia.labs.contenthub.adapters.unsplash.service.UnsplashDateTimeDeserializer;
import com.coremedia.labs.contenthub.adapters.unsplash.service.photos.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class User {

  @JsonProperty("id")
  private String id;

  @JsonProperty("updated_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime updatedAt;

  @JsonProperty("username")
  private String username;

  @JsonProperty("name")
  private String name;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("twitter_username")
  private String twitterUsername;

  @JsonProperty("portfolio_url")
  private String portfolioUrl;

  @JsonProperty("bio")
  private String bio;

  @JsonProperty("location")
  private String location;

  @JsonProperty("links")
  private Map<String, String> links;

  @JsonProperty("profile_image")
  private Map<String, String> profileImage;

  @JsonProperty("instagram_username")
  private String instagramUsername;

  @JsonProperty("total_collections")
  private int totalCollections;

  @JsonProperty("total_likes")
  private int totalLikes;

  @JsonProperty("total_photos")
  private int totalPhotos;

  @JsonProperty("accepted_tos")
  private boolean acceptedTos;

  @JsonProperty("followed_by_user")
  private boolean followedByUser;

  @JsonProperty("photos")
  private List<Photo> photos;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTwitterUsername() {
    return twitterUsername;
  }

  public void setTwitterUsername(String twitterUsername) {
    this.twitterUsername = twitterUsername;
  }

  public String getPortfolioUrl() {
    return portfolioUrl;
  }

  public void setPortfolioUrl(String portfolioUrl) {
    this.portfolioUrl = portfolioUrl;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Map<String, String> getLinks() {
    return links;
  }

  public void setLinks(Map<String, String> links) {
    this.links = links;
  }

  public Map<String, String> getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(Map<String, String> profileImage) {
    this.profileImage = profileImage;
  }

  public String getInstagramUsername() {
    return instagramUsername;
  }

  public void setInstagramUsername(String instagramUsername) {
    this.instagramUsername = instagramUsername;
  }

  public int getTotalCollections() {
    return totalCollections;
  }

  public void setTotalCollections(int totalCollections) {
    this.totalCollections = totalCollections;
  }

  public int getTotalLikes() {
    return totalLikes;
  }

  public void setTotalLikes(int totalLikes) {
    this.totalLikes = totalLikes;
  }

  public int getTotalPhotos() {
    return totalPhotos;
  }

  public void setTotalPhotos(int totalPhotos) {
    this.totalPhotos = totalPhotos;
  }

  public boolean isAcceptedTos() {
    return acceptedTos;
  }

  public void setAcceptedTos(boolean acceptedTos) {
    this.acceptedTos = acceptedTos;
  }

  public boolean isFollowedByUser() {
    return followedByUser;
  }

  public void setFollowedByUser(boolean followedByUser) {
    this.followedByUser = followedByUser;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }
}
