exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    specs: [
        "login.spec.js",
        "project-manage.spec.js"
    ],

    // CHANGE this to test server
    baseUrl: "http://localhost:8080",

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;

        // Do login for each test
        var pageUrlBase = "../../test/protractor/";
        var LoginPage = require(pageUrlBase + "login.page.js");
        var page = new LoginPage;

        page.visitPage();
        page.fillUsername("username");
        page.fillPassword("password");
        page.login();

        browser.waitForAngular();
    },

    framework: "jasmine2",

    jasmineNodeOpts: {
        onComplete: null,
        isVerbose: true,
        showColors: true,
        includeStackTrace: false
    }
};


