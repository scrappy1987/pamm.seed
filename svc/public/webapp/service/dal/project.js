"use strict";

(function () {

    angular.module("dal")
        .decorator("dal",
        ["$delegate", "servicecaller", ProjectDal]);

    function ProjectDal ($delegate, servicecaller) {
        /**
         * @param criteria
         * @returns {*}
         */
        $delegate.getProject = function (criteria) {
            return servicecaller.GET("project", criteria);
        };

        /**
         * @param projectToSave
         * @returns {*}
         */
        $delegate.saveProject = function (projectToSave) {
            projectToSave.status = "In progress";
            return servicecaller.POST("project", projectToSave);
        };

        /**
         * @param projectToDelete
         * @returns {*}
         */
        $delegate.deleteProject = function (projectToDelete) {
            return servicecaller.DELETE("project/", projectToDelete);
        };

        // Returns the decorated DAL service back to angular
        return $delegate;
    }
}());
