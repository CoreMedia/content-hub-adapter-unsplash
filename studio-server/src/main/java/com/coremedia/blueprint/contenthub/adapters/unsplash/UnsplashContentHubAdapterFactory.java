package com.coremedia.blueprint.contenthub.adapters.unsplash;

import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class UnsplashContentHubAdapterFactory implements ContentHubAdapterFactory<UnsplashContentHubSettings> {

  private static final String ADAPTER_ID = "unsplash";

  @Override
  public String getId() {
    return ADAPTER_ID;
  }

  @Override
  public ContentHubAdapter createAdapter(@NonNull UnsplashContentHubSettings settings,
                                         @NonNull String connectionId) {
    return new UnsplashContentHubAdapter(settings, connectionId);
  }
}
