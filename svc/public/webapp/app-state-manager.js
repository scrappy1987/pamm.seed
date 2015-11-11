"use strict";

(function () {
    /**
     * Set up the top level states and default root.
     */
    angular.module('app').config(function ($stateProvider, $urlRouterProvider) {

        // Default route - note that this is the URL router and therefore requires the url path not the state
        $urlRouterProvider.otherwise("login");

        $stateProvider.state("login", {
            url: "/login",
            views: {
                "content": {
                    templateUrl: "feature/login/login-index.html"
                }
            }
        })
    });
}());


(function () {

    angular.module('app').config(function ($stateProvider) {
        $stateProvider.state("home", {
            abstract: true,
            views: {
                "content": {
                    templateUrl: "feature/home/home-index.html"
                }
            }

        }).state("home.dashboard", {
            url: "/dashboard",
            views: {
                "home-content": {
                    templateUrl: "feature/dashboard/dashboard-index.html"
                }
            }
        }).state("home.project", {
            url: "/project",
            views: {
                "home-content": {
                    templateUrl: "feature/project/project-index.html"
                }
            }
        }).state("home.projectadd", {
            url: "/project/add",
            views: {
                "home-content": {
                    templateUrl: "feature/project/project-form.html"
                }
            }
        })
    });
}());
