var ErrorModule = angular.module("ErrorModule", []);

ErrorModule.controller('ErrorController', function($scope, $timeout, $location, AuthFactory) {







    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });
});
