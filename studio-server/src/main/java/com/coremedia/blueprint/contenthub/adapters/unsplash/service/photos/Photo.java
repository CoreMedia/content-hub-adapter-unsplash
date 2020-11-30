package com.coremedia.blueprint.contenthub.adapters.unsplash.service.photos;

import com.coremedia.blueprint.contenthub.adapters.unsplash.service.UnsplashDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

public class Photo {

  @JsonProperty("id")
  private String id;

  @JsonProperty("created_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime updatedAt;

  @JsonProperty("promoted_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime promotedAt;

  @JsonProperty("width")
  private int width;

  @JsonProperty("height")
  private int height;

  @JsonProperty("color")
  private String color;

  @JsonProperty("description")
  private String description;

  @JsonProperty("alt_description")
  private String altDescription;

  @JsonProperty("urls")
  private Map<String, String> urls;

  @JsonProperty("links")
  private Map<String, String> links;

  @JsonProperty("categories")
  private List<String> categories;

  @JsonProperty("likes")
  private int likes;

  @JsonProperty("liked_by_user")
  private boolean likedByUser;

  @JsonProperty("current_user_collections")
  private List<String> currentUserCollections;

  @JsonProperty("user")
  private /*User*/ Map<String, Object> user;

  @JsonProperty("sponsorship")
  private Map<String, Object> sponsorship;

  @JsonProperty("tags")
  private List<Object> tags;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getPromotedAt() {
    return promotedAt;
  }

  public void setPromotedAt(LocalDateTime promotedAt) {
    this.promotedAt = promotedAt;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAltDescription() {
    return altDescription;
  }

  public void setAltDescription(String altDescription) {
    this.altDescription = altDescription;
  }

  public Map<String, String> getUrls() {
    return urls;
  }

  public void setUrls(Map<String, String> urls) {
    this.urls = urls;
  }

  public Map<String, String> getLinks() {
    return links;
  }

  public void setLinks(Map<String, String> links) {
    this.links = links;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public boolean isLikedByUser() {
    return likedByUser;
  }

  public void setLikedByUser(boolean likedByUser) {
    this.likedByUser = likedByUser;
  }

  public List<String> getCurrentUserCollections() {
    return currentUserCollections;
  }

  public void setCurrentUserCollections(List<String> currentUserCollections) {
    this.currentUserCollections = currentUserCollections;
  }

  public Map<String, Object> getUser() {
    return user;
  }

  public void setUser(Map<String, Object> user) {
    this.user = user;
  }

  public Map<String, Object> getSponsorship() {
    return sponsorship;
  }

  public void setSponsorship(Map<String, Object> sponsorship) {
    this.sponsorship = sponsorship;
  }

  public List<Object> getTags() {
    return tags;
  }

  public void setTags(List<Object> tags) {
    this.tags = tags;
  }
}
