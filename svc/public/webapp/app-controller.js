"use strict";

(function () {
    /**
     * Global controller at index page
     */
    angular
        .module('app')
        .controller("appController",
        ["$state", "$log", AppCtrl]);


    function AppCtrl($state, $log) {
        var vm = this
        $log.debug("Application Instantiated");
    }

}());