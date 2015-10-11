"use strict";

/**
 * Manages individual projects within a ng-repeat set
 */
angular.module('app').controller("projectItemController", ["$scope", "$log", "$state", "repository", function ($scope, $log, $state, repository) {

    // name constant - for trace and debugging
    $scope.controllerName = "projectItemController";

    $scope.delete = function () {
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
                    repository.deleteProject($scope.project).then(function () {
                        waitingDialog.close();
                    }, function (error) {
                        // TODO error
                    });
                }
            }
        })
    };

    $scope.amend = function () {
        // needs to send state to amend state with the project id

        $state.go("home.project.add" , {id:$scope.project.id});
    };


}]);

