"use strict";

(function () {

    angular.module('app')
        .controller("projectFormController",
        ["$log", "repository", "$state", ProjectFormCtrl]);

    function ProjectFormCtrl($log, repository, $state) {
        var vm = this;
        
        vm.hasValidationError = false;

        vm.project = {};

        vm.saveProject = function (projectForm) {
            $log.debug("projectFormController: saveRequirement");

            vm.hasValidationError = false;
            vm.hasSubmitError = false;

            // This is the spinner - note that you probably won't see this if it is going to mock
            var waitingDialog = BootstrapDialog.show({
                message: 'Please wait - Creating Project'
            });

            repository.saveProject(vm.project).then(function (project) {
                waitingDialog.close();
                BootstrapDialog.show({
                    message: 'Project Saved successfully',
                    buttons: [{
                        id: 'button-close',
                        label: 'Close',
                        action: function (dialogWindow) {
                            dialogWindow.close();
                            $state.go("home.project");
                        }
                    }]
                });
            }, function (error) {
                // TODO show error dialog
                waitingDialog.close();
            });

            // TODO - add validation - this will need show on the HTML
        };

        vm.cancel = function (projectForm) {
            $state.go("home.project");
            $log.debug("In controller cancel");
        }
    }
}());