var ApprovedModule = angular.module("ApprovedModule", ['AuthModule', 'SliderMenuModule', 'DataModule']);

ApprovedModule.controller('ApprovedController', function($scope, $location, AuthFactory, DataFetcher) {
    if (AuthFactory.isLoggedIn() === false) $location.url('/login'); //Not logged in? Send user back.
    $scope.dirScope = { };
    $scope.dirScope.PAGE = "APPROVED"
    $scope.dirScope.USERNAME = AuthFactory.getUsername();
    $scope.PATTERNS = [];

    $scope.fetchApprovedNames = function() {
        DataFetcher.getApprovedNames(function(ret) {
            if (ret.success) {
                $scope.PATTERNS = ret.data;
                //$scope.NumberOfRows = Math.floor($scope.PATTERNS.length / 4);
                //if (($scope.PATTERNS.length % 4) > 0) $scope.NumberOfRows++;
            } else {
                $location.url('/internalerror')
            }
        })
    }

    $scope.fetchApprovedNames();
    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });
});
