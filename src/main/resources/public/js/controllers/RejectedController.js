var RejectedModule = angular.module("RejectedModule", ['AuthModule', 'SliderMenuModule', 'DataModule']);

RejectedModule.controller('RejectedController', function($scope, $location, AuthFactory, DataFetcher) {
    if (AuthFactory.isLoggedIn() === false) $location.url('/login'); //Not logged in? Send user back.
    if (AuthFactory.isAdmin() || AuthFactory.isApprover()) {
        $scope.dirScope = { };
        $scope.dirScope.PAGE = "REJECTED"
        $scope.dirScope.USERNAME = AuthFactory.getUsername();
        $scope.PATTERNS = [];

        $scope.fetchRejected = function() {
            DataFetcher.getRejected(function(ret) {
                if (ret.success) {
                    $scope.PATTERNS = ret.data;
                    //$scope.NumberOfRows = Math.floor($scope.PATTERNS.length / 4);
                    //if (($scope.PATTERNS.length % 4) > 0) $scope.NumberOfRows++;
                } else {
                    $location.url('/internalerror')
                }
            })
        }

        $scope.fetchRejected();
    } else {
        $location.url('/')
    }



    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });
});
