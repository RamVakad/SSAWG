var DataModule = angular.module("DataModule", ['AuthModule', 'LoggingModule']);

DataModule.factory("DataFetcher", function($http, AuthFactory, LoggingFactory) {
    var factory = {};

    factory.getApprovedNames = function(callback) {
        if (!AuthFactory.isLoggedIn()) return;

        $http.get('/api/patterns/approvedNames', {  }, {
            headers: {
                'Content-Type': 'application/json',
                "Accept": "*/*"
            }
        }).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    //LoggingFactory.log(JSON.stringify(response.data), "DEV");
                    callback({ success: true, data: response.data})
                }
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: approvedNames Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    factory.getPending = function(callback) {
        if (!AuthFactory.isLoggedIn()) return;

        $http.get('/api/patterns/pending', {  }, {
        }).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    //LoggingFactory.log(JSON.stringify(response.data), "DEV");
                    callback({ success: true, data: response.data})
                }
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: pending fetch Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    factory.getRejected = function(callback) {
        if (!AuthFactory.isLoggedIn()) return;

        $http.get('/api/patterns/rejected', {  }, {
        }).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    //LoggingFactory.log(JSON.stringify(response.data), "DEV");
                    callback({ success: true, data: response.data})
                }
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: rejected fetch Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    factory.getMyPending = function(callback) {
        if (!AuthFactory.isLoggedIn()) return;

        $http.get('/api/patterns/mypending', {  }, {
        }).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    //LoggingFactory.log(JSON.stringify(response.data), "DEV");
                    callback({ success: true, data: response.data})
                }
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: pendingTable Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    factory.getPatternById = function(id, callback) {
        if (!AuthFactory.isLoggedIn()) return;

        $http.get('/api/patterns/id/' + id, {  }, { }
        ).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    //LoggingFactory.log(JSON.stringify(response.data), "DEV");
                    callback({ success: true, data: response.data})
                }
            }
        }, function(response) {
            if (response.status === 422) {
                callback({ success: false, message: "Invalid Credentials" });
            } else {
                LoggingFactory.log("CRITICAL: getById Request Failed", "PROD")
                LoggingFactory.log(JSON.stringify(response), "DEV");
                callback({ success: false, message: "Internal Server Error. Please contact administrator." })
            }
        });
    }

    return factory;
});
