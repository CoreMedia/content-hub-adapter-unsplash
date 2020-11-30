package com.coremedia.blueprint.contenthub.adapters.unsplash.configuration;

import com.coremedia.blueprint.contenthub.adapters.unsplash.UnsplashContentHubAdapterFactory;
import com.coremedia.blueprint.contenthub.adapters.unsplash.UnsplashContentHubSettings;
import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnsplashContentHubAdapterConfiguration {

  @Bean
  public ContentHubAdapterFactory<UnsplashContentHubSettings> unsplashContentHubAdapterFactory() {
    return new UnsplashContentHubAdapterFactory();
  }

}
