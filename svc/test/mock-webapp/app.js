"use strict";
(function () {

    var app = angular.module("app", ["ui.router", "dal", "securityManager", "repository", "util"]).run(
        function ($window, $rootScope, $log) {
            console.log("test");
            console.log(fromState);
            console.log(toState);
        });

    //pushes token onto each http request header
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
    });

}());