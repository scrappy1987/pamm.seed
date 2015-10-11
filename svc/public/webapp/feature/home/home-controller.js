"use strict";

angular.module("app").controller("homeController", ["$scope", "$state", "$log", function ($scope, $state, $log) {

    $scope.isAtDashboard = function() {
      return $state.is("home.dashboard");
    };

    $scope.isAtProject = function() {
        return $state.includes("home.project");
    };

    $scope.navigateToDashboard = function() {
        $log.debug("Going to dahsboard");
        $state.go("home.dashboard");
    }

    $scope.navigateToAddProject = function() {
        $state.go("home.projectadd");
    }

    $scope.navigateToProject = function() {
        $log.debug("Going to project");
        $state.go("home.project");
    }

}]);

