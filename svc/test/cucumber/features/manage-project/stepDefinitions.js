var ManageProjects = require("../page-models/manage-project.page.js");
var NavBar = require("../page-models/navigation-bar.page.js");
var SetupServiceCaller = require("../util/setup-service-caller.js");
var assert = require('assert');

module.exports = function () {

    this.setDefaultTimeout(20000);
    var manageProjects = new ManageProjects();
    var setup = new SetupServiceCaller();
    var nav = new NavBar();

    this.Given(/^I am on the dashboard$/, function (callback) {
        manageProjects.visitPage().then(function () {
            callback();
        })
    });

    this.Given(/^There are no projects$/, function (callback) {
        setup.executeScript(2).then(function() {
            callback();
        });
    });

    this.Given(/^I choose sample$/, function (callback) {
        nav.clickSample().then(function () {
            callback();
        })
    });

    this.When(/^I choose manage project$/, function (callback) {
        nav.clickManageProject().then(function () {
            callback();
        })
    });

    this.Then(/^There are no projects for me to manage$/, function (callback) {
        manageProjects.getProjects().then(function (elements) {
            assert.equal(elements.length, 0);
            callback();
        })
    });

    this.Given(/^There are three projects$/, function (callback) {
        setup.executeScript(1).then(function() {
            callback();
        })
    });

    this.Then(/^There are three projects for me to manage$/, function (callback) {
        manageProjects.getProjects().then(function (elements) {
            assert.equal(elements.length, 3);
            callback();
        })
    });
};
