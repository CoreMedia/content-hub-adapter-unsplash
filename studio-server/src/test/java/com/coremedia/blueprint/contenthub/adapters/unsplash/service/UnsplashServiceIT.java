package com.coremedia.blueprint.contenthub.adapters.unsplash.service;

import com.coremedia.labs.contenthub.adapters.unsplash.service.UnsplashService;
import com.coremedia.labs.contenthub.adapters.unsplash.service.collections.Collection;
import com.coremedia.labs.contenthub.adapters.unsplash.service.photos.Photo;
import com.coremedia.labs.contenthub.adapters.unsplash.service.search.SearchResult;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnsplashServiceIT {

  private UnsplashService testling;

  @Before
  public void setUp() {
    testling = new UnsplashService("30PInu8yxAmAZpQbzWK9dYYTV5Vzl7coEWfltiqG0ms");
  }


  // --- PHOTOS ---
  @Test
  public void testListPhotos() {
    List<Photo> photos = testling.listPhotos();
    assertEquals(10, photos.size());
  }

  @Test
  public void testGetPhotoById() {
    String photoId = "MrrmLwUbyhc";
    Photo photo = testling.getPhotoById(photoId);
    assertNotNull(photo);
    assertEquals(photoId, photo.getId());
  }

  @Test
  public void testGetRandomPhoto() {
    Photo photo = testling.getRandomPhoto();
    assertNotNull(photo);
  }


  // --- COLLECTIONS ---
  @Test
  public void testListCollections() {
    List<Collection> collections = testling.listCollections();
    assertNotNull(collections);
  }

  @Test
  public void testListFeaturedCollections() {
    List<Collection> collections = testling.listFeaturedCollections();
    assertNotNull(collections);
  }

  @Test
  public void testGetCollectionById() {
    int collectionId = 9248817;
    Collection collection = testling.getCollectionById(collectionId);
    assertNotNull(collection);
    assertEquals(collectionId, collection.getId());
  }


  // --- SEARCH ---
  @Test
  public void testSearchPhotos() {
    SearchResult<Photo> hits = testling.searchPhotos("harley");
    assertNotNull(hits);
  }

}
