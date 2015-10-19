exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: [
        "login.spec.js",
        "register.spec.js"
    ],

    // CHANGE this to test server
    baseUrl: "http://localhost:63342/agilepmapp-frontend/src/mock/webapp/",

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
    },

    framework: "jasmine2",

    jasmineNodeOpts: {
        onComplete: null,
        isVerbose: true,
        showColors: true,
        includeStackTrace: false
    }
};
