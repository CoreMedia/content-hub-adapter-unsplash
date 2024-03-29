package com.coremedia.labs.plugins.adapters.unsplash.model;

import com.coremedia.contenthub.api.ContentHubBlob;
import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.UrlBlobBuilder;
import com.coremedia.contenthub.api.preview.DetailsElement;
import com.coremedia.contenthub.api.preview.DetailsSection;
import com.coremedia.labs.plugins.adapters.unsplash.service.photos.Photo;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnsplashPhotoItem extends UnsplashItem {

  private final Photo photo;

  public UnsplashPhotoItem(@NonNull ContentHubObjectId objectId, @NonNull Photo photo) {
    super(objectId, UnsplashContentHubType.PHOTO);
    this.photo = photo;
  }

  @Override
  public String getCoreMediaContentType() {
    return "CMPicture";
  }

  @Override
  public String getName() {
    String description = getDescription();
    return StringUtils.isNotBlank(description) ? description : getPhoto().getId();
  }

  @Nullable
  @Override
  public String getDescription() {
    return getPhoto().getAltDescription();
  }

  @NonNull
  @Override
  public List<DetailsSection> getDetails() {
    ContentHubBlob blob = getThumbnailBlob();

    return List.of(
            // Name & thumbnail
            new DetailsSection("main", List.of(
                    new DetailsElement<>(getName(), false, Objects.requireNonNullElse(blob, SHOW_TYPE_ICON))
            ), false, false, false),

            // Metadata
            new DetailsSection("metadata", Stream.of(
                    new DetailsElement<>("description", getPhoto().getDescription()),
                    new DetailsElement<>("dimensions", getPhoto().getWidth() + "x" + getPhoto().getHeight()),
                    new DetailsElement<>("color", getPhoto().getColor()),
                    new DetailsElement<>("createdAt", formatPreviewDate(getPhoto().getCreatedAt())),
                    new DetailsElement<>("updatedAt", formatPreviewDate(getPhoto().getUpdatedAt()))

            ).filter(p -> Objects.nonNull(p.getValue())).collect(Collectors.toUnmodifiableList())));
  }

  private String getDefaultThumbnailUrl() {
    return Optional.ofNullable(getPhoto().getUrls())
            .map(urls -> urls.get("thumb"))
            .orElse(null);
  }

  @Nullable
  private Calendar formatPreviewDate(@Nullable ZonedDateTime zdt) {
    if (zdt == null) {
      return null;
    }

    return GregorianCalendar.from(zdt);
  }

  @Nullable
  @Override
  public ContentHubBlob getThumbnailBlob() {
    return getBlob(ContentHubBlob.THUMBNAIL_BLOB_CLASSIFIER);
  }

  @Nullable
  @Override
  public ContentHubBlob getBlob(String classifier) {
    ContentHubBlob blob = null;
    try {
      blob = new UrlBlobBuilder(this, classifier).withUrl(getDefaultThumbnailUrl()).build();
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot create blob for " + this, e);
    }
    return blob;
  }

  public Photo getPhoto() {
    return photo;
  }

}
