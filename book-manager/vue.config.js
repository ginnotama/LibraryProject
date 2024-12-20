const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: {
      '/user': {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
        pathRewrite: { '/user': '/user' }
      },
      '/book': {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
        pathRewrite: { '/book': '/book' }
      },
      '/borrow': {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
        pathRewrite: { '/borrow': '/borrow' }
      },
      '/comment': {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
        pathRewrite: { '/comment': '/comment' }
      },
    }
  }
})
