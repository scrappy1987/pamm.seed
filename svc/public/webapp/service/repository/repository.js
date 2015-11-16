"use strict";

(function () {
    /**
     * The repository manages holds the context of the current work item.
     */
    angular.module("repository", ["dal"])
        .service("repository",
        ['$log', Repo]);

    function Repo($log) {
            $log.debug("Repository: Instantiated");
    }
}());
