"use strict";
(function () {

    var app = angular.module("app", ["ui.router", "dal", "securityManager", "repository"]).run(
        function ($window, $rootScope, $log) {
            $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                // override navigation here (e.g. prevent back button
                // Add security check here if necessary
            });

            // store constants in root scope so that child compoents can access it
            $rootScope.$$errorText = $$errorText;
            $rootScope.$$dataType = $$dataType;

            $log.debug("App Instantiated");
        });

    //pushes token onto each http request header
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
    });

}());