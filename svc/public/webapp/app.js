"use strict";
(function () {

    var app = angular.module("app", ["ui.router", "dal", "securityManager", "repository", "util", "sse"]).run(
        function ($window, $rootScope, $log, $location) {
            $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                if(fromState.url.indexOf("/") > -1)
                {
                    if(fromState.url !== $location.url())
                    {
                        event.preventDefault();
                    }
                }
            });


            $rootScope.$$errorText = $$errorText;
            $rootScope.$$dataType = $$dataType;

            $log.debug("App Instantiated");
        });

    //pushes token onto each http request header
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
    });

}());
