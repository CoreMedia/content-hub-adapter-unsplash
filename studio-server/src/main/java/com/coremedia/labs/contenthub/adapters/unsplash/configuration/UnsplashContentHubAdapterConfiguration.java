package com.coremedia.labs.contenthub.adapters.unsplash.configuration;

import com.coremedia.labs.contenthub.adapters.unsplash.UnsplashContentHubAdapterFactory;
import com.coremedia.labs.contenthub.adapters.unsplash.UnsplashContentHubSettings;
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
