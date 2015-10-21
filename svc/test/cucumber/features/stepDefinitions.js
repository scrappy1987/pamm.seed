// Do login for each test

var LoginPage = require("./login.page.js");
var assert = require('assert');

module.exports = function() {

    this.Before(function (event, callback) {
        console.log("this is the before scenario");
        callback();
    });

    var login = new LoginPage;

    this.Given(/I am on the login page/, function(callback) {
        login.visitPage().then(function() {
            callback();
        });
    });

    this.Then(/^I enter valid credentials into the input fields$/, function (callback) {
        login.fillUsername("admin").then(function() {
            callback();
        });
        login.fillPassword("admin").then(function() {
            callback();
        });
    });

    this.Then(/^I click the login button$/, function (callback) {
        login.login().then(function() {
            callback();
        });
    });

    this.Then(/^I should be redirected to dashboard$/, function (callback) {
        login.currentURL().then(function(currentURL) {
            assert.equal("http://localhost:9000/#/dashboard", currentURL);
            callback();
        });
    });

};
