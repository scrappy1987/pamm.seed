"use strict";

/**
 * Global controller at index page
 */
angular.module('app').controller("appController", ["$scope", "$state", "$log", function ($scope, $state, $log) {
    $log.debug("Application Instantiated");
}]);
