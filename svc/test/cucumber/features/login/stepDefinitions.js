// Do login for each test

var LoginPage = require("../page-models/login.page.js");
var assert = require('assert');

module.exports = function() {

    var login = new LoginPage;

    this.Given(/I am on the login page/, function(callback) {
        login.visitPage().then(function() {
            callback();
        });
    });

    this.Given(/^I enter valid credentials into the input fields$/, function (callback) {
        login.fillInDetails("admin", "password").then(function() {
            callback();
        })
    });

    this.When(/^I click the login button$/, function (callback) {
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
