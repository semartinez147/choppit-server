const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(createProxyMiddleware('/api', {
        logLevel: 'debug',
        target: "http://localhost:8888",
        changeOrigin: true,
        secure: true,
    }));
    app.listen(3000)
};