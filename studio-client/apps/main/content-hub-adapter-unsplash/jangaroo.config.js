const { jangarooConfig } = require("@jangaroo/core");

module.exports = jangarooConfig({
  type: "code",
  sencha: {
    name: "com.coremedia.labs.plugins__studio-client.content-hub-adapter-unsplash",
    namespace: "com.coremedia.labs.plugins.adapters.unsplash.client",
    studioPlugins: [
      {
        mainClass: "com.coremedia.labs.plugins.adapters.unsplash.client.ContentHubUnsplashStudioPlugin",
        name: "Content Hub - Unsplash",
      },
    ],
  },
  command: {
    build: {
      ignoreTypeErrors: true
    },
  },
});
