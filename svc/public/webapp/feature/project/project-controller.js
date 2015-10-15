"use strict";

(function () {
    /**
     * project Controller - this manages the default Reauirements pages that shows all the projects
     */
    angular.module('app')
        .controller("projectController",
        ["$log", "repository", ProjectCtrl]);

    function ProjectCtrl($log, repository) {
        var vm = this;
        // Constant - for trace and debug
        vm.controllerName = "projectController";

        // init get all user projects
        repository.getProject().then(function (results) {
            $log.debug("Retrieving projects from repository");
            vm.projects = results;
        }, function (error) {
            vm.error = true;
            vm.errorMessage = error;
        });
    }
}());