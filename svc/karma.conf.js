module.exports = function(config) {
    config.set({
        basePath: 'src',
        frameworks: ['jasmine'],
        browsers: ['Chrome'],
        autoWatch: true,
        exclude: [
            'main/webapp/lib/bootstrap-3.3.5-dist/js/npm.js'
        ],
        files: [
            { pattern: 'main/webapp/lib/angular-1.4.4/angular.min.js'},
            { pattern: 'main/webapp/lib/jquery/jquery-2.1.4.min.js'},
            { pattern: 'main/webapp/lib/**/*.js'},
            //{ pattern: 'main/webapp/resource/**/*'},
            { pattern: 'main/webapp/constant/**/*.js'},
            { pattern: 'main/webapp/app.js'},
            { pattern: 'main/webapp/app-controller.js'},
            { pattern: 'main/webapp/app-state-manager.js'},
            { pattern: 'main/webapp/service/**/*.js'},
            { pattern: 'main/webapp/feature/**/*.js'},
            { pattern: 'main/webapp/index.html'},
            { pattern: 'test/**/*.js'}
        ],
        plugins: [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine'
        ],
        reporters: ['progress', 'html']
    });
};