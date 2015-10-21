exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    capabilities: {
        'browserName': 'chrome'
    },

    specs: [
        "login.spec.js",
        "project-manage.spec.js"
    ],

    // CHANGE this to test server
    baseUrl: "http://localhost:9000",

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;

        // Do login for each test
        var pageUrlBase = "../../test/e2e/";
        var LoginPage = require(pageUrlBase + "login.page.js");
        var page = new LoginPage;
        var request = require('request');
        beforeEach(function(){
            request({
                url: 'http://localhost:9000/project', //URL to hit
                method: 'POST',
                //Lets post the following key/values as form
                json:  {
                    "title":"SET-UP5",
                    "summary":"SET-UP5",
                    "info":"SET-UP5",
                    "status":"SET-UP5"
                }
            }, function(error, response, body){
                if(error) {
                    console.log("ERROR ERROR ERROR ERROR ERROR ERROR ");
                    console.log(error);
                } else {
                    console.log("SUCCESS SUCCESS SUCCESS SUCCESS SUCCESS SUCCESS");
                    console.log(response.statusCode, body);
                }
            });
        });
        request({
            url: 'http://localhost:9000/project/1', //URL to hit
            method: 'DELETE'
        }, function(error, response, body){
            if(error) {
                console.log("ERROR ERROR ERROR ERROR ERROR ERROR ");
                console.log(error);
            } else {
                console.log("SUCCESS SUCCESS SUCCESS SUCCESS SUCCESS SUCCESS");
                console.log(response.statusCode, body);
            }
        });


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


