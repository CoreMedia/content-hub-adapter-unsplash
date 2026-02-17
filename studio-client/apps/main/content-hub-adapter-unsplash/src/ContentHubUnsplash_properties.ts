import SvgIconUtil from "@coremedia/studio-client.base-models/util/SvgIconUtil";
import { personalFolderPicture, typePicture } from "@coremedia/studio-client.common-icons";
import icon from "./icons/unsplash.svg";

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
  folder_type_photo_icon: SvgIconUtil.getIconStyleClassForSvgIcon(personalFolderPicture),
  item_type_photo_name: "Photo",
  item_type_photo_icon: SvgIconUtil.getIconStyleClassForSvgIcon(typePicture),
  description_sectionItemKey: "Description",
  dimensions_sectionItemKey: "Dimensions",
  color_sectionItemKey: "Color",
  createdAt_sectionItemKey: "Created at",
  updatedAt_sectionItemKey: "Updated at",
};

export default ContentHubUnsplash_properties;
