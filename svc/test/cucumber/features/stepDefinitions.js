var assert = require('assert');

// Do login for each test
var pageUrlBase = "../../test/cucumber/";
var LoginPage = require(pageUrlBase + "login.page.js");


//page.visitPage();
//page.fillUsername("username");
//page.fillPassword("password");
//page.login();

browser.waitForAngular();

module.exports = function() {

    this.registerHandler('AfterScenario', function (event, callback) {
        // clear localStorage
        browser.executeScript('window.localStorage.clear();');
        callback();
    });

    var login = new LoginPage;

    this.Given(/I am on the login page/, function(next) {
        login.visitPage().then(function() {
            next();
        });
    });



};
