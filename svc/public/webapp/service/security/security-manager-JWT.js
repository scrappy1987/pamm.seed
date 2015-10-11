angular.module("securityManager")
    .decorator("securityManager", function ($delegate) {

        var login = $delegate.login;

        $delegate.login = function (username, password) {
            console.log("Extended");
            return login(username, password);
        };

        return $delegate;

    });