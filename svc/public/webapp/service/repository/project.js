"use strict";

(function () {
    /**
     * Project Repository
     */
    angular.module("repository")
        .decorator("repository",
        ["$delegate", "$q", "$log", "dal", ProjectRepo]);

    function ProjectRepo($delegate, $q, $log, dal) {

        var projectCache = [];
        console.log("This is project cache");
        /**
         *
         * @param criteria
         * @returns {*}
         */
        $delegate.getProject = function (criteria) {
            $log.debug("Repository:Project getProject");

            var deferred = $q.defer();
            dal.getProject(criteria).then(function (results) {

                // This is a data change - broadcast events here if your application requires components communication
                projectCache = results;
                deferred.resolve(results);
            }, function (error) {
                deferred.reject(error);
            });

            return deferred.promise;
        };

        /**
         * Create or update requirement.  A requirement with no ID is new.
         * @returns {{}}
         */
        $delegate.saveProject = function (projectToSave) {
            $log.debug("Repository:Project - saveProject");

            var deferred = $q.defer();
            var isUpdate = projectToSave.hasOwnProperty("id");


            $log.debug("isUpdate = " + isUpdate);
            $log.debug(JSON.stringify(projectToSave));

            dal.saveProject(projectToSave).then(function (project) {
                // Add newly created project to cache
                if (!isUpdate) {
                    projectCache.push(project);
                }
                deferred.resolve(project);
            }, function (error) {
                deferred.reject(error);
            });

            return deferred.promise;
        };

        /**
         * Delete the given project
         * @param projectToDelete
         * @returns {*}
         */
        $delegate.deleteProject = function (projectToDelete) {
            $log.debug("Repository:Project - deleteProject");

            var deferred = $q.defer();
            dal.deleteProject(projectToDelete).then(function (projects) {
                _.remove(projectCache, {
                    id: projectToDelete.id
                });

                deferred.resolve(projectCache);
            }, function (error) {
                deferred.reject(error);
            });

            return deferred.promise;
        };

        $log.debug("Repository:Project Instantiated");
        return $delegate;
    }
}());