"use strict";

/**
 * project Controller - this manages the default Reauirements pages that shows all the projects
 */
angular.module('app').controller("projectController", ["$scope", "$log", "repository", function ($scope, $log, repository) {

    // Constant - for trace and debug
    $scope.controllerName = "projectController";

    // init get all user projects
    repository.getProject().then(function (results) {

        $log.debug("Retrieving projects from repository");
        $scope.projects = results;
    }, function (error) {
        $scope.error = true;
        $scope.errorMessage = error;
    });
}]);

