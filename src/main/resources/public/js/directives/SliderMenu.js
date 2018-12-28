var SliderMenuModule = angular.module("SliderMenuModule", ['AuthModule']);

SliderMenuModule.directive('sliderMenu', function(AuthFactory) {
    return {
        restrict: 'E',
        scope: {
            dirScope: '=info'
        },
        controller: function($scope, $http, $location, AuthFactory) {
            $scope.logout = function() {
                AuthFactory.signOut();
            }
        },
        templateUrl: "partials/SliderMenu.html"
    }
});
