"use strict";
(function () {

    var app = angular.module("app", ["ui.router", "dal", "securityManager", "repository"]).run(
        function ($window, $rootScope, $log) {
        });

    //pushes token onto each http request header
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
    });

}());