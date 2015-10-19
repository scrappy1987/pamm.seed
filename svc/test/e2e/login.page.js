"use strict";

module.exports = (function () {
    function LoginPage() {
        this.usernameField = element(by.model("credentials.username"));
        this.passwordField = element(by.model("credentials.password"));
        this.loginButton = element(by.buttonText("Login"));
        this.registerButton = element(by.buttonText("Register"));
    }

    LoginPage.prototype.visitPage = function () {
        browser.get("index.html");
    };

    LoginPage.prototype.fillUsername = function (username) {
        this.usernameField.sendKeys(username);
    };

    LoginPage.prototype.fillPassword = function (password) {
        if (password == null) {
            password = "password";
        }
        this.passwordField.sendKeys(password);
    };

    LoginPage.prototype.login = function () {
        this.loginButton.click();
    };

    LoginPage.prototype.register = function () {
        this.registerButton.click();
    };
    return LoginPage;
})();

