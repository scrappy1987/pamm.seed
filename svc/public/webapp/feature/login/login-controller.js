"use strict";

/**
 * Global controller
 */
angular.module("app").controller("loginController", ["$scope", "$state", "$log", "securityManager", function ($scope, $state, $log, securityManager) {
    $scope.hasValidationError = false;
    $scope.hasAuthenticationError = false;

    $scope.errorText = {
        invalidCredentials : $$errorText.INVALID_CREDENTIALS,
        emptyCredentials : $$errorText.EMPTY_CRENDTIALS
    };
    /**
     *
     */
    $scope.register = function () {
        $state.go("register");
    };

    /**
     * Perform login
     * @param form The login form
     */
    $scope.login = function (form) {

        $scope.hasValidationError = false;
        $scope.hasAuthenticationError = false;

        //if (form.$valid) {
        //    securityManager.login($scope.credentials.username, $scope.credentials.password).then(
        //        function (response) {
        //            $log.debug("Login successful");
                    $state.go("home.dashboard");
        //        }, function (response) {
        //            $log.debug("Login failed");
        //            $scope.hasAuthenticationError = true;
        //        }
        //    );
        //} else {
        //    $scope.hasValidationError = true;
        //}
    };
}]);

