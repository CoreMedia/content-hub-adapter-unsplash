package com.coremedia.labs.plugins.adapters.unsplash.service;

import com.coremedia.labs.plugins.adapters.unsplash.service.collections.Collection;
import com.coremedia.labs.plugins.adapters.unsplash.service.photos.Photo;
import com.coremedia.labs.plugins.adapters.unsplash.service.photos.PhotoOrientation;
import com.coremedia.labs.plugins.adapters.unsplash.service.photos.PhotosFilter;
import com.coremedia.labs.plugins.adapters.unsplash.service.search.SearchResult;
import com.coremedia.labs.plugins.adapters.unsplash.service.users.User;
import edu.umd.cs.findbugs.annotations.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * Service class wrapping all API calls to Unsplash API.
 * See <a href="https://unsplash.com/documentation">Unsplash API Documentation</a> for details.
 */
@Service
public class UnsplashService {

  private static final Logger LOG = LoggerFactory.getLogger(UnsplashService.class);

  private static final String DEFAULT_API_ENDPOINT = "https://api.unsplash.com/";
  private static final String DEFAULT_API_VERSION = "v1";

  private RestTemplate restTemplate;
  private final String apiEndpoint;
  private final String apiVersion;
  private final String clientId;

  public UnsplashService(@NonNull String clientId) {
    this(DEFAULT_API_ENDPOINT, DEFAULT_API_VERSION, clientId);
  }

  public UnsplashService(@NonNull String apiEndpoint, @NonNull String apiVersion, @NonNull String clientId) {
    this.apiEndpoint = apiEndpoint;
    this.apiVersion = apiVersion;
    this.clientId = clientId;
    this.restTemplate = new RestTemplate();
  }


  // --- PHOTOS --------------------------------------------------------------------------------------------------------

  public enum Photos_OrderBy {
    latest, oldest, popular
  }

  private static final int DEFAULT_PHOTOS_PAGE = 1;
  private static final int DEFAULT_PHOTOS_PER_PAGE = 10;
  private static final Photos_OrderBy DEFAULT_PHOTOS_ORDER_BY = Photos_OrderBy.latest;

  /**
   * Get a single page from the list of all photos.
   *
   * @return list of {@link Photo}s
   */
  public List<Photo> listPhotos() {
    return listPhotos(DEFAULT_PHOTOS_PAGE, DEFAULT_PHOTOS_PER_PAGE, DEFAULT_PHOTOS_ORDER_BY);
  }

  /**
   * Get a single page from the list of all photos.
   *
   * @param page page index
   * @param perPage number of photos per page
   * @param orderBy ordering criteria
   *
   * @return list of {@link Photo}s
   */
  public List<Photo> listPhotos(int page, int perPage, Photos_OrderBy orderBy) {
    ResponseEntity<List<Photo>> response = performApiCall("/photos",
            null,
            Map.of(
                    "page", page,
                    "per_page", perPage
            ),
            new ParameterizedTypeReference<List<Photo>>() {});
    return response.getBody();
  }

  /**
   * Retrieve a single photo.
   *
   * @param photoId The photo’s ID. Required.
   *
   * @return the {@link Photo} or <code>null</code>
   */
  public Photo getPhotoById(@NonNull String photoId) {
    ResponseEntity<Photo> response = performApiCall("/photos/{id}",
            Map.of("id", photoId),
            null,
            Photo.class);
    if (response.getStatusCode().equals(HttpStatus.OK)) {
      return response.getBody();
    } else {
      return null;
    }
  }

  /**
   * Retrieve a single random photo.
   *
   * @return a random {@link Photo}
   */
  public Photo getRandomPhoto() {
    return getRandomPhoto(PhotosFilter.DEFAULT);
  }

  /**
   * Retrieve a single random photo with the provided filters.
   *
   * @param filter filter options
   *
   * @return a random {@link Photo}
   */
  public Photo getRandomPhoto(PhotosFilter filter) {
    ResponseEntity<Photo> response = performApiCall("/photos/random",
            null,
            filter.toParams(),
            Photo.class);
    return response.getBody();
  }


  // --- COLLECTIONS ---------------------------------------------------------------------------------------------------

  private static final int DEFAULT_COLLECTIONS_PAGE = 1;
  private static final int DEFAULT_COLLECTIONS_PER_PAGE = 10;

  /**
   * Get a single page from the list of all collections.
   *
   * @return
   */
  public List<Collection> listCollections() {
    return listCollections(DEFAULT_COLLECTIONS_PAGE, DEFAULT_COLLECTIONS_PER_PAGE);
  }

  public List<Collection> listCollections(int page, int perPage) {
    ResponseEntity<List<Collection>> response = performApiCall("/collections",
            null,
            Map.of(
                    "page", page,
                    "per_page", perPage

            ),
            new ParameterizedTypeReference<List<Collection>>() {});
    return response.getBody();
  }

  /**
   * Get a single page from the list of featured collections.
   *
   * @return
   */
  public List<Collection> listFeaturedCollections() {
    return listCollections(DEFAULT_COLLECTIONS_PAGE, DEFAULT_COLLECTIONS_PER_PAGE);
  }

  public List<Collection> listFeaturedCollections(int page, int perPage) {
    ResponseEntity<List<Collection>> response = performApiCall("/collections/featured",
            null,
            Map.of(
                    "page", page,
                    "per_page", perPage

            ),
            new ParameterizedTypeReference<List<Collection>>() {});
    return response.getBody();
  }

  /**
   * Retrieve a single collection.
   *
   * @param collectionId The collections’s ID. Required.
   * @return
   */
  public Collection getCollectionById(int collectionId) {
    ResponseEntity<Collection> response = performApiCall("/collections/{id}",
            Map.of("id", collectionId),
            null,
            Collection.class);
    return response.getBody();
  }

  /**
   * Retrieve a collection’s photos.
   *
   * @param collectionId
   * @return
   */
  public List<Photo> getCollectionPhotos(@NonNull String collectionId) {
    return getCollectionPhotos(collectionId, DEFAULT_COLLECTIONS_PAGE, DEFAULT_COLLECTIONS_PER_PAGE, PhotoOrientation.ALL);
  }

  public List<Photo> getCollectionPhotos(@NonNull String collectionId, int page, int perPage, PhotoOrientation orientation) {
    ResponseEntity<List<Photo>> response = performApiCall("/collections/{id}/photos",
            Map.of("id", collectionId),
            null,
            new ParameterizedTypeReference<List<Photo>>() {});
    return response.getBody();
  }

  public List<Collection> getRelatedCollections(@NonNull String collectionId) {
    ResponseEntity<List<Collection>> response = performApiCall("/collections/{id}/related",
            Map.of("id", collectionId),
            null,
            new ParameterizedTypeReference<List<Collection>>() {});
    return response.getBody();
  }


  // --- SEARCH --------------------------------------------------------------------------------------------------------

  public enum Search_Photos_OrderBy {
    latest, relevant
  }

  private enum Search_Photos_Color {
    black_and_white, black, white, yellow, orange, red, purple, magenta, green, teal, blue
  }

  private static final int DEFAULT_SEARCH_RESULT_PAGE = 1;
  private static final int DEFAULT_SEARCH_RESULT_PER_PAGE = 10;
  private static final Search_Photos_OrderBy DEFAULT_SEARCH_RESULT_ORDER_BY = Search_Photos_OrderBy.relevant;

  /**
   * Get a single page of photo results for a query.
   *
   * @param query
   * @return
   */
  public SearchResult<Photo> searchPhotos(@NonNull String query) {
    return searchPhotos(query, DEFAULT_SEARCH_RESULT_PAGE, DEFAULT_SEARCH_RESULT_PER_PAGE, DEFAULT_SEARCH_RESULT_ORDER_BY);
  }

  public SearchResult<Photo> searchPhotos(@NonNull String query, int page, int perPage, Search_Photos_OrderBy orderBy) {
    if (StringUtils.isBlank(query)) {
      return new SearchResult<>();
    }

    ResponseEntity<SearchResult<Photo>> response = performApiCall("/search/photos",
            null,
            Map.of(
                    "query", query,
                    "page", page,
                    "per_page", perPage,
                    "order_by", orderBy
            ),
            new ParameterizedTypeReference<SearchResult<Photo>>() {});
    return response.getBody();
  }

  /**
   * Get a single page of collection results for a query.
   *
   * @param query
   * @return
   */
  public SearchResult<Collection> searchCollections(@NonNull String query) {
    return searchCollections(query, DEFAULT_SEARCH_RESULT_PAGE, DEFAULT_SEARCH_RESULT_PER_PAGE);
  }

  public SearchResult<Collection> searchCollections(@NonNull String query, int page, int perPage) {
    ResponseEntity<SearchResult<Collection>> response = performApiCall("/search/collections",
            null,
            Map.of(
                    "query", query,
                    "page", page,
                    "per_page", perPage
            ),
            new ParameterizedTypeReference<SearchResult<Collection>>() {});
    return response.getBody();
  }

  /**
   * Get a single page of user results for a query.
   *
   * @param query
   * @return
   */
  public SearchResult<User> searchUsers(@NonNull String query) {
    return searchUsers(query, DEFAULT_SEARCH_RESULT_PAGE, DEFAULT_SEARCH_RESULT_PER_PAGE);
  }

  public SearchResult<User> searchUsers(@NonNull String query, int page, int perPage) {
    ResponseEntity<SearchResult<User>> response = performApiCall("/search/collections",
            null,
            Map.of(
                    "query", query,
                    "page", page,
                    "per_page", perPage
            ),
            new ParameterizedTypeReference<SearchResult<User>>() {});
    return response.getBody();
  }


  // --- INTERNAL ------------------------------------------------------------------------------------------------------

  private <T> ResponseEntity<T> performApiCall(String path, Class<T> responseType) {
    return performApiCall(path, Map.of(), Map.of(), responseType);
  }

  private <T> ResponseEntity<T> performApiCall(String path, Map<String, Object> pathVariables, Map<String, Object> queryParams, Class<T> responseType) {
    return performApiCall(path, pathVariables, queryParams, ParameterizedTypeReference.forType(responseType));
  }

  private <T> ResponseEntity<T> performApiCall(String path, Map<String, Object> pathVariables, Map<String, Object> queryParams, ParameterizedTypeReference<T> responseType) {
    if (pathVariables == null) {
      pathVariables = Map.of();
    }

    if (queryParams == null) {
      queryParams = Map.of();
    }

    // Build headers
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId);
    headers.set("Accept-Version", apiVersion);

    // Build request entity
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    // Build request URI
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(buildUrl(path));

    // Add query params
    for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
      uriBuilder.queryParam(queryParam.getKey(), queryParam.getValue());
    }

    // perform request
    String url = uriBuilder.buildAndExpand(pathVariables).toUriString();
    LOG.debug("GET - {}", url);
    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
  }

  private String buildUrl(String path) {
    return apiEndpoint + path;
  }

}
