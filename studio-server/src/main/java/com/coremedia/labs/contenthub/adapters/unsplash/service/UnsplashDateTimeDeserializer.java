package com.coremedia.labs.contenthub.adapters.unsplash.service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.format.DateTimeFormatter;

public class UnsplashDateTimeDeserializer extends LocalDateTimeDeserializer {

  public UnsplashDateTimeDeserializer() {
    super(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  protected UnsplashDateTimeDeserializer(DateTimeFormatter formatter) {
    super(formatter);
  }

  protected UnsplashDateTimeDeserializer(LocalDateTimeDeserializer base, Boolean leniency) {
    super(base, leniency);
  }
}
