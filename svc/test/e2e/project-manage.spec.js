"use strict";

var pageUrlBase = "../../test/e2e/";
var NavBar = require(pageUrlBase + "navigation-bar.page.js");

describe("When user selects requirement from the navbar", function () {
    var navbar = new NavBar();

    it("should navigate to requirement page", function () {
        navbar.navigateToRequirement();
        browser.waitForAngular();

        browser.getCurrentUrl().then(console.log);
        expect(browser.getCurrentUrl()).toMatch("/project");
    });
});
