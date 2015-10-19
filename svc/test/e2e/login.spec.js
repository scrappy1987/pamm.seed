"use strict";

var pageUrlBase = "../../test/e2e/";
var LoginPage = require(pageUrlBase + "login.page.js");

describe("At login", function () {
    var page = new LoginPage();
    it("should navigate to dashboard after login with correct credentials", function () {
        page.visitPage();
        page.fillUsername("username");
        page.fillPassword("password");
        page.login();

        browser.waitForAngular();
        browser.getCurrentUrl().then(console.log);
        expect(browser.getCurrentUrl()).toMatch("/dashboard");
    });
});
