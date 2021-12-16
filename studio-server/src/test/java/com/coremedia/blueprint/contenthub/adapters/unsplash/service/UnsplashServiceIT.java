package com.coremedia.blueprint.contenthub.adapters.unsplash.service;

import com.coremedia.labs.plugins.adapters.unsplash.service.UnsplashService;
import com.coremedia.labs.plugins.adapters.unsplash.service.collections.Collection;
import com.coremedia.labs.plugins.adapters.unsplash.service.photos.Photo;
import com.coremedia.labs.plugins.adapters.unsplash.service.search.SearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnsplashServiceIT {

  private UnsplashService testling;

  @BeforeEach
  public void setUp() {
    testling = new UnsplashService("");
  }


  // --- PHOTOS ---
  @Test
  @Disabled
  public void testListPhotos() {
    List<Photo> photos = testling.listPhotos();
    assertEquals(10, photos.size());
  }

  @Test
  @Disabled
  public void testGetPhotoById() {
    String photoId = "MrrmLwUbyhc";
    Photo photo = testling.getPhotoById(photoId);
    assertNotNull(photo);
    assertEquals(photoId, photo.getId());
  }

  @Test
  @Disabled
  public void testGetRandomPhoto() {
    Photo photo = testling.getRandomPhoto();
    assertNotNull(photo);
  }


  // --- COLLECTIONS ---
  @Test
  @Disabled
  public void testListCollections() {
    List<Collection> collections = testling.listCollections();
    assertNotNull(collections);
  }

  @Test
  @Disabled
  public void testListFeaturedCollections() {
    List<Collection> collections = testling.listFeaturedCollections();
    assertNotNull(collections);
  }

  @Test
  @Disabled
  public void testGetCollectionById() {
    int collectionId = 9248817;
    Collection collection = testling.getCollectionById(collectionId);
    assertNotNull(collection);
    assertEquals(collectionId, collection.getId());
  }


  // --- SEARCH ---
  @Test
  @Disabled
  public void testSearchPhotos() {
    SearchResult<Photo> hits = testling.searchPhotos("harley");
    assertNotNull(hits);
  }

}
