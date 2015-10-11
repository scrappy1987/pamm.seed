"use strict";

/**
 * The repository manages holds the context of the current work item.
 */

angular.module("repository", [ "dal" ]).service("repository", ['$log',
    function ($log) {
        $log.debug("Repository: Instantiated");
    }]);
