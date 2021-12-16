package com.coremedia.labs.plugins.adapters.unsplash.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Folder;
import edu.umd.cs.findbugs.annotations.NonNull;

public class UnsplashFolder extends UnsplashContentHubObject implements Folder {

  private final ContentHubType type;
  private final String name;

  public UnsplashFolder(@NonNull ContentHubObjectId objectId, String name, UnsplashContentHubType type) {
    super(objectId);
    this.name = name;
    this.type = type.getType();
  }

  @Override
  public String getName() {
    return name;
  }

  @NonNull
  @Override
  public ContentHubType getContentHubType() {
    return type;
  }

}
