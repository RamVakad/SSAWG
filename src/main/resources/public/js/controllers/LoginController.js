var LoginModule = angular.module("LoginModule", ['AuthModule', 'ngAnimate']);

LoginModule.controller('LoginController', function($scope, $timeout, $location, AuthFactory) {
    $scope.ShowMessage = false;
    $scope.userName = "";
    $scope.passWord = "";

    $scope.LoginBtn = function() {
        AuthFactory.signIn($scope.userName, $scope.passWord, function(res) {
            $scope.ResponseMessage = res.message;
            $scope.ShowMessage = true;
            $timeout(function() {
                $scope.ShowMessage = false;
                if (res.success) {
                    $location.url('/home');
                }
            }, 1500);
        })
    }



    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });
});
