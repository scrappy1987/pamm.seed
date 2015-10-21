"use strict";

module.exports = (function () {
    function LoginPage() {
        this.usernameField = element(by.model("loginCtrl.username"));
        this.passwordField = element(by.model("loginCtrl.password"));
        this.loginButton = element(by.buttonText("Login"));
    }

    LoginPage.prototype.visitPage = function () {
        return browser.get("/");
    };

    LoginPage.prototype.fillUsername = function (username) {
        return this.usernameField.sendKeys(username);
    };

    LoginPage.prototype.fillPassword = function (password) {
        if (password == null) {
            password = "password";
        }
        return this.passwordField.sendKeys(password);
    };

    LoginPage.prototype.login = function () {
        return this.loginButton.click();
    };

    LoginPage.prototype.currentURL = function (){
        return browser.getCurrentUrl();
    };

    return LoginPage;
})();

