"use strict";

(function () {
    angular.module("sse", [])
        .service("sseConnectionManager",
        ["$rootScope", SSEConnectionManager]);

    function SSEConnectionManager($rootScope) {

        this.createConnection = function(username){
            var sseEventSource = new EventSource("http://localhost:9000/sse-connection/" + username);
            sseEventSource.onmessage = function(event) {
               $rootScope.$broadcast('new-work-item', event.data);
            }
        };
    }
}());