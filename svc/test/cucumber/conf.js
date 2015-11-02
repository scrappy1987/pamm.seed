exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    capabilities: {
        browserName: 'chrome'
    },

    // CHANGE this to test server
    baseUrl: "http://localhost:9000",

    specs: [
        'features/**/*.feature'
    ],

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;
    },

    allScriptsTimeout: 11000,

    getPageTimeout: 30000,

    framework: 'cucumber',

    cucumberOpts: {
        require: 'features/**/stepDefinitions.js',
        format: 'pretty' // or summary
    },

    jasmineNodeOpts: {
        // If true, display spec names.
        isVerbose: false,
        // If true, print colors to the terminal.
        showColors: true,
        // If true, include stack traces in failures.
        includeStackTrace: true,
        // Default time to wait in ms before a test fails.
        defaultTimeoutInterval: 30000
    }
};


