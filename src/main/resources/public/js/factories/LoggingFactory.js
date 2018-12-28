var LoggingModule = angular.module("LoggingModule", []);

LoggingModule.factory("LoggingFactory", function() {
    var factory = {};
    factory.log = function(msg, type) {
        if (SSWAG.Logging.indexOf(type) === -1) return;
        console.log(msg);
    }
    return factory;
});
