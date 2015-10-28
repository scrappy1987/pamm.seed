var ManageProjects = require("../page-models/manage-project.page.js");
var NavBar = require("../page-models/navigation-bar.page.js");
var SetupServiceCaller = require("../util/setup-service-caller.js");
var assert = require('assert');

module.exports = function() {

    var manageProjects = new ManageProjects();
    var setup = new SetupServiceCaller();
    var nav = new NavBar();

    this.Given(/^I am on the dashboard$/, function (callback) {
        manageProjects.visitPage().then(function() {
            callback();
        })
    });

    this.Given(/^No projects are currently defined$/, function (callback) {
        setup.executeScript(2);
        callback();
    });

    this.When(/^I click on sample$/, function (callback) {
      nav.clickSample().then(function(){
            callback();
      })
    });

    this.Then(/^I click on manage project$/, function (callback) {
      nav.clickManageProject().then(function(){
            callback();
      })
    });

    this.Then(/^It takes me to an empty project list$/, function (callback) {
     manageProjects.getProjects().then(function(elements) {
        assert.equal(elements.length, 0);
        callback();
      })
    });

    this.Given(/^there has been three projects already added$/, function (callback) {
        setup.executeScript(1);
        callback();
    });

    this.Then(/^It takes me to a project list with three projects already populated$/, function (callback) {
        manageProjects.getProjects().then(function(elements) {
        assert.equal(elements.length, 3);
        callback();
        })
    });
};
