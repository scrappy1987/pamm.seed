"use strict";

var pageUrlBase = "../../test/protractor/";
var NavBar = require(pageUrlBase + "navigation-bar.page.js");
var AddProject = require(pageUrlBase + "add-project.page.js");

describe("When user selects sample from the navbar", function () {
    var navbar = new NavBar();
    var addProject = new AddProject();

    it("should navigate to manage project", function () {
        navbar.navigateToManageProject();
        browser.waitForAngular();
        expect(browser.getCurrentUrl()).toMatch("/project");
    });

    it("should navigate to add project", function () {
        navbar.navigateToAddProject();
        browser.waitForAngular();
        expect(browser.getCurrentUrl()).toMatch("/project/add");
    });


    it('Should navigate to add project and add given item', function() {
        navbar.navigateToAddProject();
        //There is 3 mock projects before adding an additional project
        addProject.addProject("mock title", "mock summary", "mock info");
        addProject.closeModal('button-close');
        expect(browser.getCurrentUrl()).toMatch("/project");
        var todoList = element.all(by.repeater('project in projectCtrl.projects'));
        expect(todoList.count()).toEqual(4);
    });
});
