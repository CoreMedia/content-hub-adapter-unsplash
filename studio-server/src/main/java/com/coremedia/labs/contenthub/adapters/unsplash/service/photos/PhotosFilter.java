package com.coremedia.labs.contenthub.adapters.unsplash.service.photos;

import com.google.common.base.Joiner;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * All parameters are optional, and can be combined to narrow the pool of photos from which a random one will be chosen.
 * <p>
 * Note: You can’t use the collections and query parameters in the same request.
 */
public class PhotosFilter {

  private static final int DEFAULT_COUNT = 1;
  private static final int MAX_COUNT = 30;

  private static final String COLLECTIONS = "collections";
  private static final String FEATURED = "featured";
  private static final String USERNAME = "username";
  private static final String QUERY = "query";
  private static final String ORIENTATION = "orientation";
  private static final String COUNT = "count";

  public static PhotosFilter DEFAULT = new PhotosFilter();

  /**
   * Public collection ID(‘s) to filter selection.
   */
  private List<String> collections;

  /**
   * Limit selection to featured photos.
   */
  private boolean featured;

  /**
   * Limit selection to a single user.
   */
  private String username;

  /**
   * Limit selection to photos matching a search term.
   */
  private String query;

  /**
   * Filter search results by photo orientation.
   */
  private PhotoOrientation orientation;

  /**
   * The number of photos to return. (Default: 1; max: 30)
   */
  private int count;


  public PhotosFilter() {
    this.collections = new ArrayList<>();
    this.count = 1;
  }

  public PhotosFilter(List<String> collections) {
    this();
    this.collections = collections;
  }

  public PhotosFilter(String query) {
    this();
    this.query = query;
  }

  public List<String> getCollections() {
    return collections;
  }

  public boolean isFeatured() {
    return featured;
  }

  public void setFeatured(boolean featured) {
    this.featured = featured;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getQuery() {
    return query;
  }

  public PhotoOrientation getOrientation() {
    return orientation;
  }

  public void setOrientation(PhotoOrientation orientation) {
    this.orientation = orientation;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    if (count >= DEFAULT_COUNT && count <= MAX_COUNT) {
      this.count = count;
    }
  }

  public Map<String, Object> toParams() {
    Map<String, Object> parameterMap = new HashMap<>();
    Joiner joiner = Joiner.on(",");

    // Either collections or query can be defined at a time.
    if (CollectionUtils.isNotEmpty(collections)) {
      parameterMap.put(COLLECTIONS, joiner.join(collections));
    } else {
      parameterMap.put(QUERY, getQuery());
    }

    if (isFeatured()) {
      parameterMap.put(FEATURED, isFeatured());
    }

    parameterMap.put(USERNAME, getUsername());
    parameterMap.put(ORIENTATION, getOrientation());
    parameterMap.put(COUNT, getCount());

    // Remove null values
    parameterMap.values().removeIf(Objects::isNull);

    return parameterMap;
  }

}
