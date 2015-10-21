exports.config = {

    // CHANGE this to test server
    baseUrl: "http://localhost:9000",

    seleniumAddress: 'http://localhost:4444/wd/hub',

    specs: [
        'features/*.feature'
    ],

    capabilities: {
        browserName: 'chrome'
    },

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;
    },

    framework: 'cucumber',

    cucumberOpts: {
        require: 'features/stepDefinitions.js',
        format: 'pretty' // or summary
    }
};


