"use strict";

(function () {
    angular.module("app")
        .controller("homeController",
        ["$state", "$log", '$window', HomeCtrl]);

    function HomeCtrl($state, $log, $window) {
        var vm = this;
        
        vm.isAtDashboard = function () {
            return $state.is("home.dashboard");
        };

        vm.isAtProject = function () {
            return $state.includes("home.project");
        };

        vm.navigateToDashboard = function () {
            $log.debug("Going to dahsboard");
            $state.go("home.dashboard");
        };

        vm.navigateToAddProject = function () {
            $state.go("home.projectadd");
        };

        vm.navigateToProject = function () {
            $log.debug("Going to project");
            $state.go("home.project");
        };

        vm.logout = function () {
            delete $window.sessionStorage.token;
            $log.debug("Logging out");
            $state.go("login");
        };
    }
}());