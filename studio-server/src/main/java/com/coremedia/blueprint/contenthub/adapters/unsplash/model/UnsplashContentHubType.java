package com.coremedia.blueprint.contenthub.adapters.unsplash.model;

import com.coremedia.contenthub.api.ContentHubType;

public enum UnsplashContentHubType {

  COLLECTION(new ContentHubType("collection")),
  PHOTO(new ContentHubType("photo")),
  SEARCH(new ContentHubType("search")),
  USER(new ContentHubType("user")),
  ROOT(new ContentHubType("root"));

  private ContentHubType type;

  UnsplashContentHubType(ContentHubType type) {
    this.type = type;
  }

  public ContentHubType getType() {
    return type;
  }

}
