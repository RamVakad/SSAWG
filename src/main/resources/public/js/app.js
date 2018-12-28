var SSWAG = angular.module('SSWAG', ['ngRoute', 'LoginModule', 'ApprovedModule', 'PendingModule', 'NewPatternModule', 'PatternDetailModule', 'ErrorModule', 'RejectedModule']);

SSWAG.config(function($routeProvider, $locationProvider) {
    $routeProvider.
    when('/', {
        redirectTo: '/approved'
    }).
    when('/internalerror', {
        templateUrl: 'partials/internalerror.html',
        controller: 'ErrorController'
    }).
    when('/login', {
        templateUrl: 'partials/Login.html',
        controller: 'LoginController'
    }).
    when('/detail/:id', {
        templateUrl: 'partials/PatternDetail.html',
        controller: 'PatternDetailController'
    }).
    when('/approved', {
        templateUrl: 'partials/Approved.html',
        controller: 'ApprovedController'
    }).
    when('/rejected', {
        templateUrl: 'partials/Rejected.html',
        controller: 'RejectedController'
    }).
    when('/pending', {
        templateUrl: 'partials/Pending.html',
        controller: 'PendingController'
    }).
    when('/newpattern', {
        templateUrl: 'partials/NewPattern.html',
        controller: 'NewPatternController'
    }).
    otherwise({
        redirectTo: '/'
    });
    $locationProvider.html5Mode(true);
});

SSWAG.Logging = "PROD DEV VERBOSE TEST"; // "PROD DEV VERBOSE TEST"