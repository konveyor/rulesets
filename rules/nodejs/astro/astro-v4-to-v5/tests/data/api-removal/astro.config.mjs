import { defineConfig } from 'astro/config';

// astro-4-to-astro-5-api-removal-00020
export default defineConfig({
  output: 'hybrid',
  experimental: {
    env: {
      schema: {}
    },
    serverIslands: true,
    directRenderScript: true,
    contentLayer: true
  }
});
