// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
module.exports = {
  devServer: {
    // Paths
    // assetsSubDirectory: 'static',
    // static: {
    //   directory: path.resolve(__dirname, 'static'), // 정적 파일 디렉토리 설정
    // },
    // assetsPublicPath: '/',
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // your address
        // changeOrigin: true,
        // pathRewrite: {
        //   '^/api': '', // url
        // },
      },
    },
  },
};