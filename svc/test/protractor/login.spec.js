"use strict";


describe("At login", function () {

    /**
     * As all tests need to login into the application; this has been put into standard
     * setup in the onprepare function in the webdriver configuration.
     */

    it("Should navigate to dashboard after login with correct credentials", function () {
        expect(browser.getCurrentUrl()).toMatch("/dashboard");
    });
});
