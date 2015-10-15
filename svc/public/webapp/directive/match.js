"use strict";
(function () {
    /**
     * TODO needs comment
     */
    angular.module('app')
        .directive('match', [Match]);

    function Match () {
        return {
            require: 'ngModel',
            link: function (scope, elem, attrs, ngModel) {

                ngModel.$parsers.unshift(validate);

                //Watch the main ngModel to be matched against so that validate is called when it is changed too
                scope.$watch(attrs.match, function () {
                    validate(ngModel.$viewValue);
                });

                /**
                 *
                 * @param value
                 * @returns {*}
                 */
                function validate(value) {
                    var isValid = scope.$eval(attrs.match) == value;

                    ngModel.$setValidity('match', isValid);

                    return isValid ? value : undefined;
                }
            }
        };
    };
}());