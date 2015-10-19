"use strict";

module.exports = (function () {
    function NavBar() {
        this.requirementLink = element(by.linkText("Requirement"));
        this.manageRequirementLink = element(by.linkText("Manage Requirements"));
    }

    NavBar.prototype.navigateToRequirement = function () {
        this.requirementLink.click();
        this.manageRequirementLink.click();
    };

    return NavBar;
})();

