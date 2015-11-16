"use strict";

(function () {
    angular.module("util", [])
        .service("servicecaller",
        ["$http", "$q", ServiceCaller]);

    function ServiceCaller($http, $q) {
        /**
         * @returns {promise}
         */
        this.GET = function (apiPath, criteria) {
            var deferred = $q.defer();
            if (criteria == undefined || criteria == null) {
                $http.get(apiPath).then(function (result) {
                    deferred.resolve(result.data);
                }, function (e) {
                    deferred.reject(e);
                });
            } else {
                deferred.reject("The criteria is undefined or null");
            }
            return deferred.promise;
        };

        /**
         * @returns {promise}
         */
        this.POST = function (apiPath, itemToSave) {
            var deferred = $q.defer();
            $http.post(apiPath, itemToSave).then(function (itemId) {
                itemToSave.id = itemId.data.id;
                deferred.resolve(itemToSave);
            }, function (e) {
                deferred.reject(e);
            });
            return deferred.promise;
        };

        /**
         * @returns {promise}
         */
        this.DELETE = function (apiPath, itemToDelete) {
            var deferred = $q.defer();
            $http.delete(apiPath + itemToDelete.id).then(function () {
                deferred.resolve();
            }, function (e) {
                deferred.reject(e);
            });
            return deferred.promise;
        };

    }
}());
