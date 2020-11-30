package com.coremedia.blueprint.contenthub.adapters.unsplash.service.collections;

import com.coremedia.blueprint.contenthub.adapters.unsplash.service.UnsplashDateTimeDeserializer;
import com.coremedia.blueprint.contenthub.adapters.unsplash.service.photos.Photo;
import com.coremedia.blueprint.contenthub.adapters.unsplash.service.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Collection {

  @JsonProperty("id")
  private int id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("published_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime publishedAt;

  @JsonProperty("updated_at")
  @JsonDeserialize(using = UnsplashDateTimeDeserializer.class)
  private LocalDateTime updatedAt;

  @JsonProperty("curated")
  private boolean curated;

  @JsonProperty("featured")
  private boolean featured;

  @JsonProperty("total_photos")
  private int totalPhotos;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("share_key")
  private String shareKey;

  @JsonProperty("tags")
  private List<Object> tags;

  @JsonProperty("links")
  private Map<String, String> links;

  @JsonProperty("user")
  private User user;

  @JsonProperty("cover_photo")
  private Photo coverPhoto;

  @JsonProperty("preview_photos")
  private List<Photo> previewPhotos;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public LocalDateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(LocalDateTime publishedAt) {
    this.publishedAt = publishedAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isCurated() {
    return curated;
  }

  public void setCurated(boolean curated) {
    this.curated = curated;
  }

  public boolean isFeatured() {
    return featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

  public int getTotalPhotos() {
    return totalPhotos;
  }

  public void setTotalPhotos(int totalPhotos) {
    this.totalPhotos = totalPhotos;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
  }

  public String getShareKey() {
    return shareKey;
  }

  public void setShareKey(String shareKey) {
    this.shareKey = shareKey;
  }

  public List<Object> getTags() {
    return tags;
  }

  public void setTags(List<Object> tags) {
    this.tags = tags;
  }

  public Map<String, String> getLinks() {
    return links;
  }

  public void setLinks(Map<String, String> links) {
    this.links = links;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Photo getCoverPhoto() {
    return coverPhoto;
  }

  public void setCoverPhoto(Photo coverPhoto) {
    this.coverPhoto = coverPhoto;
  }

  public List<Photo> getPreviewPhotos() {
    return previewPhotos;
  }

  public void setPreviewPhotos(List<Photo> previewPhotos) {
    this.previewPhotos = previewPhotos;
  }
}
