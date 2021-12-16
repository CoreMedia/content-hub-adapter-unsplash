import ContentHub_properties from "@coremedia/studio-client.main.content-hub-editor-components/ContentHub_properties";
import CopyResourceBundleProperties from "@coremedia/studio-client.main.editor-components/configuration/CopyResourceBundleProperties";
import StudioPlugin from "@coremedia/studio-client.main.editor-components/configuration/StudioPlugin";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import resourceManager from "@jangaroo/runtime/l10n/resourceManager";
import ContentHubUnsplash_properties from "./ContentHubUnsplash_properties";

interface ContentHubUnsplashStudioPluginConfig extends Config<StudioPlugin> {
}

class ContentHubUnsplashStudioPlugin extends StudioPlugin {
  declare Config: ContentHubUnsplashStudioPluginConfig;

  static readonly xtype: string = "com.coremedia.blueprint.studio.contenthub.unsplash.ContentHubUnsplashStudioPlugin";

  constructor(config: Config<ContentHubUnsplashStudioPlugin> = null) {
    super(ConfigUtils.apply(Config(ContentHubUnsplashStudioPlugin, {

      configuration: [
        new CopyResourceBundleProperties({
          destination: resourceManager.getResourceBundle(null, ContentHub_properties),
          source: resourceManager.getResourceBundle(null, ContentHubUnsplash_properties),
        }),
      ],

    }), config));
  }
}

export default ContentHubUnsplashStudioPlugin;
