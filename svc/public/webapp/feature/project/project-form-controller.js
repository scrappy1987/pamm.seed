"use strict";

angular.module('app').controller("projectFormController", ["$scope", "$log", "repository", "$state", function ($scope, $log, repository, $state) {
    $scope.hasValidationError = false;

    $scope.project = {};

    $scope.saveProject = function (projectForm) {
        $log.debug("projectFormController: saveRequirement");

        //if (projectForm.$valid) {
        $scope.hasValidationError = false;
        $scope.hasSubmitError = false;

        // This is the spinner - note that you probably won't see this if it is going to mock
        var waitingDialog = BootstrapDialog.show({
            message: 'Please wait - Creating Project'
        });

        repository.saveProject($scope.project).then(function (project) {
            waitingDialog.close();
            BootstrapDialog.show({
                message: 'Project Saved successfully',
                buttons: [{
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
        //} else {
        //    $scope.hasValidationError = true;
        //}
    };

    $scope.cancel = function () {
        $log.debug("In controller cancel");
    }
}]);

