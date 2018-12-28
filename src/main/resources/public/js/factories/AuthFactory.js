var AuthModule = angular.module("AuthModule", ['LoggingModule', 'ngCookies']);

AuthModule.factory("AuthFactory", function($http, $location, LoggingFactory, $cookies) {
    var factory = {};
    factory.Authorized = false;
    factory.UserInfo = null;
    factory.Token = $cookies.get('sswag_JWT');

    factory.fetchUserInfo = function(callback) {
        $http.get('/api/users/me').then(function(response) {
            LoggingFactory.log(response.data, "TEST")
            factory.UserInfo = response.data;
            callback();
        }, function(error) {
            if (error.status === 500) {
                $cookies.remove('sswag_JWT');
                $location.url('/')
            }
        });
    }

    if (factory.Token) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + factory.Token;
        factory.Authorized = true;
        factory.fetchUserInfo(function() { });
    }

    factory.signOut = function() {
        $http.defaults.headers.common.Authorization = null;
        factory.Authorized = false;
        $cookies.remove('sswag_JWT');
        $location.url('/')
    }

    factory.signIn = function(user, pass, callback) {
        $http.post('/api/users/signin', {  }, {
            params : {
                username: user, password: pass
            },
            headers: {
                'Content-Type': 'application/json',
                "Accept": "*/*"
            }
        }).then(function (response) {
            LoggingFactory.log(JSON.stringify(response.data), "TEST")
            if(response.status === 200) {
                LoggingFactory.log("Sign-In Success", "TEST")
                factory.Token = response.data;
                factory.Authorized = true;
                $http.defaults.headers.common.Authorization = 'Bearer ' + factory.Token;
                $cookies.put('sswag_JWT', factory.Token, { secure: true });
                factory.fetchUserInfo(function() {
                    callback({ success: true, message: "Success" });
                });
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: Sign-In POST Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    factory.isLoggedIn = function() {
        return factory.Authorized;
    }

    factory.getUserId = function() {
        if (!factory.UserInfo) return false;
        return factory.UserInfo.id;
    }

    factory.getUsername = function() {
        if (factory.UserInfo)
        return factory.UserInfo.username;
        else return '';
    }

    factory.getEmail = function() {
        if (factory.UserInfo == null) return false;
        return factory.UserInfo.email;
    }

    factory.isApprover = function() {
        if (factory.UserInfo == null) return false;
        var ROLES = factory.UserInfo.roles;
        for(var i = 0; i < ROLES.length; i++) {
            if (ROLES[i] === "ROLE_APPROVER") return true;
        }
        return false;
    }

    factory.isAdmin = function() {
        if (factory.UserInfo == null) return false;
        var ROLES = factory.UserInfo.roles;
        for(var i = 0; i < ROLES.length; i++) {
            if (ROLES[i] === "ROLE_ADMIN") return true;
        }
        return false;
    }


    return factory;
});
