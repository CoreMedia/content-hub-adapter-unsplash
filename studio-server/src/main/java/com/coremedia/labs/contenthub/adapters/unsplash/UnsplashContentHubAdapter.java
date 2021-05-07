package com.coremedia.labs.contenthub.adapters.unsplash;

import com.coremedia.labs.contenthub.adapters.unsplash.model.UnsplashContentHubType;
import com.coremedia.labs.contenthub.adapters.unsplash.model.UnsplashFolder;
import com.coremedia.labs.contenthub.adapters.unsplash.model.UnsplashPhotoItem;
import com.coremedia.labs.contenthub.adapters.unsplash.service.UnsplashService;
import com.coremedia.labs.contenthub.adapters.unsplash.service.photos.Photo;
import com.coremedia.labs.contenthub.adapters.unsplash.service.search.SearchResult;
import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubContext;
import com.coremedia.contenthub.api.ContentHubObject;
import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubTransformer;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Folder;
import com.coremedia.contenthub.api.GetChildrenResult;
import com.coremedia.contenthub.api.Item;
import com.coremedia.contenthub.api.exception.ContentHubException;
import com.coremedia.contenthub.api.pagination.PaginationRequest;
import com.coremedia.contenthub.api.search.ContentHubSearchResult;
import com.coremedia.contenthub.api.search.ContentHubSearchService;
import com.coremedia.contenthub.api.search.Sort;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UnsplashContentHubAdapter implements ContentHubAdapter, ContentHubSearchService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UnsplashContentHubAdapter.class);

  private final String connectionId;
  private final UnsplashContentHubSettings settings;

  private UnsplashService unsplashService;

  private UnsplashFolder rootFolder;
  private UnsplashFolder collectionsRootFolder;
  private UnsplashFolder photosRootFolder;
  private UnsplashFolder usersRootFolder;

  private List<Folder> topFolders;

  public UnsplashContentHubAdapter(UnsplashContentHubSettings settings, String connectionId) {
    this.settings = settings;
    this.connectionId = connectionId;

    rootFolder = new UnsplashFolder(new ContentHubObjectId(connectionId, "root"), settings.getDisplayName(), UnsplashContentHubType.ROOT);
    collectionsRootFolder = new UnsplashFolder(new ContentHubObjectId(connectionId, "collections"), "Collections", UnsplashContentHubType.COLLECTION);
    photosRootFolder = new UnsplashFolder(new ContentHubObjectId(connectionId, "photos"), "Photos", UnsplashContentHubType.PHOTO);
    usersRootFolder = new UnsplashFolder(new ContentHubObjectId(connectionId, "users"), "Users", UnsplashContentHubType.USER);

    topFolders = new ArrayList<>();
//    topFolders.add(collectionsRootFolder);
    topFolders.add(photosRootFolder);
//    topFolders.add(usersRootFolder);

    unsplashService = new UnsplashService(settings.getClientId());
  }

  // --- ContentHubAdapter ---------------------------------------------------------------------------------------------

  @Override
  public Folder getRootFolder(ContentHubContext context) throws ContentHubException {
    return rootFolder;
  }

  @Nullable
  @Override
  public Folder getFolder(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    return getRootFolder(context);
  }

  public List<Folder> getSubFolders(ContentHubContext context, Folder folder) throws ContentHubException {
    if (rootFolder == folder) {
      return topFolders;
    } else {
      return Collections.emptyList();
    }
  }

  @Nullable
  @Override
  public Folder getParent(ContentHubContext context, ContentHubObject contentHubObject) throws ContentHubException {
    return rootFolder == contentHubObject ? null : getRootFolder(context);
  }

  public List<Item> getItems(ContentHubContext context, Folder folder) throws ContentHubException {
    List<Item> items = Collections.emptyList();

    if (photosRootFolder == folder) {
      items = unsplashService.listPhotos(1, 100, UnsplashService.Photos_OrderBy.latest)
              .stream()
              .map(this::createPhotoItem)
              .collect(Collectors.toUnmodifiableList());
    }

    return items;
  }

  @Nullable
  @Override
  public Item getItem(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    return Optional.ofNullable(unsplashService.getPhotoById(id.getExternalId())).map(this::createPhotoItem).orElse(null);
  }

  @Override
  public GetChildrenResult getChildren(ContentHubContext context, Folder folder, @Nullable PaginationRequest paginationRequest) {
    List<ContentHubObject> children = new ArrayList<>();
    children.addAll(getSubFolders(context, folder));
    children.addAll(getItems(context, folder));
    return new GetChildrenResult(children);
  }

  @Override
  public ContentHubTransformer transformer() {
    return new UnsplashContentHubTransformer();
  }


  // --- ContentHubSearchService ---------------------------------------------------------------------------------------

  private static final List<ContentHubType> SEARCH_TYPES = List.of(
          UnsplashContentHubType.PHOTO.getType()
//          UnsplashContentHubType.COLLECTION.getType(),
//          UnsplashContentHubType.USER.getType()
  );

  @NonNull
  @Override
  public Optional<ContentHubSearchService> searchService() {
    return Optional.of(this);
  }

  @Override
  public ContentHubSearchResult search(@NonNull String query,
                                       @Nullable Folder belowFolder,
                                       @Nullable ContentHubType type,
                                       Collection<String> filterQueries,
                                       List<Sort> sortCriteria,
                                       int limit) {

    ContentHubSearchResult result = new ContentHubSearchResult(Collections.emptyList());

    if (StringUtils.isNotBlank(query)) {
      SearchResult<Photo> searchResult = unsplashService.searchPhotos(query, 1, limit > 0 ? limit : 10, UnsplashService.Search_Photos_OrderBy.relevant);
      List<Item> hits = searchResult.getResults().stream().map(this::createPhotoItem).collect(Collectors.toUnmodifiableList());
      result = new ContentHubSearchResult(hits, searchResult.getTotal());
    }


    return result;
  }

  @Override
  public boolean supportsSearchBelowFolder() {
    return false;
  }

  @Override
  public Collection<ContentHubType> supportedTypes() {
    return SEARCH_TYPES;
  }

  // --- INTERNAL ------------------------------------------------------------------------------------------------------

  private UnsplashPhotoItem createPhotoItem(@NonNull Photo photo) {
    ContentHubObjectId hubId = new ContentHubObjectId(connectionId, photo.getId());
    return new UnsplashPhotoItem(hubId, photo);
  }

}
