"use strict";

(function () {
    /**
     * Manages individual projects within a ng-repeat set
     */
    angular.module('app')
        .controller("projectItemController",
        ["$log", "$state", "repository", ProjectItemCtrl]);

    function ProjectItemCtrl($log, $state, repository) {
        var vm = this;
        // name constant - for trace and debugging
        vm.controllerName = "projectItemController";

        vm.delete = function (project) {
            var waitingDialog;
            BootstrapDialog.confirm({
                message: 'Are you sure that you want to delete this project?',
                type: BootstrapDialog.TYPE_WARNING,
                btnOKLabel: 'Delete!',
                btnOKClass: 'btn-warning',
                callback: function (confirmed) {
                    if (confirmed) {
                        waitingDialog = BootstrapDialog.show({
                            message: 'Please wait - Deleting project'
                        });
                        repository.deleteProject(project).then(function () {
                            waitingDialog.close();
                        }, function (error) {
                            // TODO error
                        });
                    }
                }
            })
        };

        vm.amend = function (project) {
            // needs to send state to amend state with the project id
            $state.go("home.project.add", {id: project.id});
        };
    }

}());