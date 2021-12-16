import SvgIconUtil from "@coremedia/studio-client.cap-base-models/util/SvgIconUtil";
import CoreIcons_properties from "@coremedia/studio-client.core-icons/CoreIcons_properties";
import icon from "./icons/unsplash_16.svg";

interface ContentHubUnsplash_properties {

  adapter_type_unsplash_name: string;
  adapter_type_unsplash_icon: string;
  folder_type_photo_name: string;
  folder_type_photo_icon: string;
  item_type_photo_name: string;
  item_type_photo_icon: string;
  description_sectionItemKey: string;
  dimensions_sectionItemKey: string;
  color_sectionItemKey: string;
  createdAt_sectionItemKey: string;
  updatedAt_sectionItemKey: string;
}

const ContentHubUnsplash_properties: ContentHubUnsplash_properties = {
  adapter_type_unsplash_name: "Unsplash",
  adapter_type_unsplash_icon:  SvgIconUtil.getIconStyleClassForSvgIcon(icon),
  folder_type_photo_name: "Photos",
  folder_type_photo_icon: CoreIcons_properties.personal_folder_picture,
  item_type_photo_name: "Photo",
  item_type_photo_icon: CoreIcons_properties.type_picture,
  description_sectionItemKey: "Description",
  dimensions_sectionItemKey: "Dimensions",
  color_sectionItemKey: "Color",
  createdAt_sectionItemKey: "Created at",
  updatedAt_sectionItemKey: "Updated at",
};

export default ContentHubUnsplash_properties;
