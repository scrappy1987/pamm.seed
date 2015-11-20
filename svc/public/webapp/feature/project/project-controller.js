"use strict";

(function () {
    /**
     * project Controller - this manages the default Reauirements pages that shows all the projects
     */
    angular.module('app')
        .controller("projectController",
        ["$log", "$scope", "repository", ProjectCtrl]);

    function ProjectCtrl($log, $scope, repository ) {
        var vm = this;

        vm.controllerName = "projectController";

        repository.getProject().then(function (results) {
             vm.projects = results;
         }, function (error) {
             vm.error = true;
             vm.errorMessage = error;
         });

         $scope.$on('new-work-item', function(event, data) {
            vm.sseproject = JSON.parse(data);
            delete vm.sseproject.username;
            vm.projects.push(vm.sseproject);
            $scope.$apply();
         });
    }
}());