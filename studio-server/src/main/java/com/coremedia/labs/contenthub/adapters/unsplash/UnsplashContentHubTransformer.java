package com.coremedia.labs.contenthub.adapters.unsplash;

import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubBlob;
import com.coremedia.contenthub.api.ContentHubContentCreationException;
import com.coremedia.contenthub.api.ContentHubContext;
import com.coremedia.contenthub.api.ContentHubObject;
import com.coremedia.contenthub.api.ContentHubTransformer;
import com.coremedia.contenthub.api.ContentModel;
import com.coremedia.contenthub.api.ContentModelReference;
import com.coremedia.contenthub.api.Item;
import com.coremedia.contenthub.api.UrlBlobBuilder;
import com.coremedia.labs.contenthub.adapters.unsplash.model.UnsplashItem;
import com.coremedia.labs.contenthub.adapters.unsplash.model.UnsplashPhotoItem;
import com.coremedia.labs.contenthub.adapters.unsplash.service.photos.Photo;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UnsplashContentHubTransformer implements ContentHubTransformer {

  private static final Logger LOG = LoggerFactory.getLogger(UnsplashContentHubTransformer.class);

  @Nullable
  @Override
  public ContentModel transform(Item source, ContentHubAdapter contentHubAdapter, ContentHubContext contentHubContext) throws ContentHubContentCreationException {
    if (!(source instanceof UnsplashItem)) {
      throw new IllegalArgumentException("Cannot transform source " + source);
    }

    UnsplashItem item = (UnsplashItem) source;
    LOG.info("Creating content model for item {}.", item);

    ContentModel contentModel = ContentModel.createContentModel(item);
    contentModel.put("title", item.getName());

    if (item instanceof UnsplashPhotoItem) {
      Photo photo = ((UnsplashPhotoItem) item).getPhoto();

      ContentHubBlob blob = item.getBlob("file");
      contentModel.put("alt", Optional.ofNullable(photo.getAltDescription()).orElse(photo.getDescription()));

      if (blob != null) {
        contentModel.put("data", blob);
      }
    }

    return contentModel;
  }

  @Nullable
  @Override
  public ContentModel resolveReference(ContentHubObject owner, ContentModelReference reference, ContentHubAdapter contentHubAdapter, ContentHubContext contentHubContext) {
    Object data = reference.getData();
    if (!(data instanceof String)) {
      throw new IllegalArgumentException("Not my reference: " + reference);
    }

    String imageUrl = (String) data;
    String imageName = reference.getOwner().getContentName() + " (Thumbnail)";

    ContentModel referenceModel = ContentModel.createReferenceModel(imageName, reference.getCoreMediaContentType());
    referenceModel.put("data", new UrlBlobBuilder(owner, "thumbnail").withUrl(imageUrl).build());
    referenceModel.put("title", "Video Thumbnail " + imageName);

    return referenceModel;
  }
}
