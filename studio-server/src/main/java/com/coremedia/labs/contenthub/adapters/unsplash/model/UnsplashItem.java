package com.coremedia.labs.contenthub.adapters.unsplash.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Item;
import edu.umd.cs.findbugs.annotations.NonNull;

abstract class UnsplashItem extends UnsplashContentHubObject implements Item {

  private UnsplashContentHubType type;

  public UnsplashItem(@NonNull ContentHubObjectId objectId, UnsplashContentHubType type) {
    super(objectId);
    this.type = type;
  }

  @Override
  public ContentHubType getContentHubType() {
    return type.getType();
  }

}
