describe('login', function() {

    beforeEach(module('app'));

    var $controller, controller;
    var $scope, $state, repository;
    var $httpBackend;
    var $q, deferred;

    //To jasmine test promises, use the below example. //When backend is finished
    var promise = function(response, resolved) {
        deferred = $q.defer();
        if (resolved == true) {
            deferred.resolve(response);
        }
        else {
            deferred.reject(response);
        }
        return deferred.promise;
    };

    beforeEach(inject(function($rootScope, _$controller_, _$state_, _repository_, _$httpBackend_, _$q_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.when('GET', 'feature/login/login-index.html').respond('200');
        $httpBackend.when('GET', 'feature/register/register-index.html').respond('200');
        $httpBackend.flush();

        $q = _$q_;
        $scope = $rootScope;
        $state = _$state_;
        repository = _repository_;
        $controller = _$controller_;
        controller = $controller('loginController', { $scope: $scope, $state: $state, repository: repository});
    }));

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe('$scope.register', function() {

        it('should go to the register state', function() {
            spyOn($state, 'go');

            $scope.register();
            $scope.$apply();

            expect($state.go).toHaveBeenCalledWith('register');
        });

    });

    describe('$scope.login', function() {

        it('should set the session and go to the home state', function() {
            spyOn($state, 'go');
            spyOn(repository, 'login').and.returnValue(promise($scope, true));

            var form = {$valid: true};
            $scope.login(form);
            $scope.$apply();

            //For some reason the values are equal but the type is not, so toBe fails
            expect($state.go).toHaveBeenCalledWith('home.dashboard');
        });

        it('should try to login but return user not authorised', function() {
            spyOn(repository, 'login').and.returnValue(promise($scope, false));

            var form = {$valid: true};
            $scope.login(form);
            $scope.$apply();

            expect($scope.hasValidationError).toBe("You either aren't registered or authorised");
        });

        it('should not login', function() {
            var form = {$valid: false};
            $scope.login(form);
            $scope.$apply();

            expect($scope.hasValidationError).toBe("Username and Password can't be empty");
        });

    });

});
