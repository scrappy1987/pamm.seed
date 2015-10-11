"use strict";

angular.module("securityManager", []).service("securityManager", ["$q", "$rootScope", "$state", "$location", "$log", "$http",
    function ($q, $rootScope, $state, $location, $log, $http) {
        var hasAuthenticated = false;
        var userCredentials;

        this.getUserCredentials = function () {
            return userCredentials;
        };

        this.setUserCredentials = function (credentials) {
            userCredentials = credentials;
        };

        this.login = function (username, password) {

            userCredentials = {
                username: username,
                password: password
            };

            // replace real http promise here
            var deferred = $q.defer();
            try {
                hasAuthenticated = true;

                // replace with commented out code for real authentication
                // See security-manager-JWT for example of "plugging" in other mechanism
                deferred.resolve;

                //$http.post('/api/account/login', userCredentials).then(function(response) {
                //    deferred.resolve(response);
                //}, function(response) {
                //    deferred.reject(response);
                //});
            } catch (e) {
                deferred.reject(e);
            }
            return deferred.promise;
        };

        //$rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //    var loginState = (toState.name === "login") || (toState.name === "register");
        //    if (!hasAuthenticated && !loginState) {
        //        $log.debug("Unauthenticated - Prevented navigation");
        //        event.preventDefault();
        //        $state.go("login");
        //    } else if (hasAuthenticated && loginState) {
        //        $log.debug("User has been authenticated - Prevented navigation to login");
        //        event.preventDefault();
        //    }
        //});
    }
]);

