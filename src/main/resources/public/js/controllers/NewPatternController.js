var NewPatternModule = angular.module("NewPatternModule", ['AttachmentModule', 'AuthModule', 'SliderMenuModule']);

NewPatternModule.controller('NewPatternController', function(AttachmentFactory, $http, $window, $scope, $location, AuthFactory) {
    if (AuthFactory.isLoggedIn() === false) $location.url('/login'); //Not logged in? Send user back.
    $scope.dirScope = { };
    $scope.dirScope.PAGE = "NEWPATTERN"
    $scope.dirScope.USERNAME = AuthFactory.getUsername();
    $scope.pattern = {};
    $scope.pattern.name = "";
    $scope.pattern.prolog = "";
    $scope.pattern.problem = "";
    $scope.pattern.forces = "";
    $scope.pattern.solution = "";
    $scope.pattern.examples = "";
    $scope.pattern.classification = "";
    $scope.pattern.classOther = "";
    $scope.pattern.securityDomain = "";
    $scope.pattern.securityOther = "";
    $scope.pattern.category = "";
    $scope.pattern.languages = "";
    $scope.pattern.resultingContext = "";
    $scope.pattern.codeRepository = "";
    $scope.pattern.howTo = "";
    $scope.pattern.threatModel = "";
    $scope.pattern.testCases = "";
    $scope.pattern.testScripts = "";
    $scope.pattern.attachments = [];
    $scope.pattern.attachmentsTemp = "";

    $scope.submit = function () {
        //console.log(JSON.stringify($scope.pattern));
        $scope.pattern.classification = "CLASS_" + $scope.pattern.classification;
        $scope.pattern.securityDomain = "SEC_" + $scope.pattern.securityDomain;
        $http({
            method: 'POST',
            url: '/api/patterns/new',
            data: $scope.pattern,
            headers: {
                'Content-Type': 'application/json',
                "Accept": "*/*"
            }
        }).then(function (success) {
            $window.alert("Pattern Submitted Successfully.");
            $location.url('/pending')
        }, function (error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }

    $scope.removeFile = function(id) {
        $http.get('/api/attachments/delete/' + id, { headers: {
                "Accept": "*/*"
            } }, { }
        ).then(function (response) {
            if(response.status === 200) {
                if(response.status === 200) {
                    for(var i = 0; i < $scope.pattern.attachments.length; i++) {
                        if ($scope.pattern.attachments[i].id === id) {
                            $scope.pattern.attachments.splice(i, 1);
                        }
                    }
                }
            }
        }, function(error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }

    $scope.uploading = false;
    $scope.fileUploadMessage = "Add File"
    $scope.newFile = function(element) {
        if (!$scope.uploading) {
            $scope.uploading = true;
            $scope.fileUploadMessage = "Uploading"
            $scope.$apply(function(scope) {
                var file = element.files[0];
                var fileName = file.name;
                var fileData = new Blob([file]);
                var reader = new FileReader();

                reader.onload = function(e) {
                    var arrayBuffer = e.target.result;
                    var bytes = new Uint8Array(arrayBuffer);
                    bytes = Array.from(bytes);
                    var atm = {
                        fileName: fileName,
                        bitsInBytes: bytes,
                        contentType: file.type
                    };
                    $http({
                        method: 'POST',
                        url: '/api/attachments/upload',
                        data: atm,
                        headers: {
                            'Content-Type': 'application/json',
                            "Accept": "*/*"
                        }
                    }).then(function (success) {
                        $scope.pattern.attachments.push(success.data);
                        $scope.fileUploadMessage = "Add File";
                        $scope.uploading = false;
                    }, function (error) {
                        $location.url('/internalerror')
                        console.log(error);
                    });
                };

                reader.readAsArrayBuffer(fileData);
            });
        }
    }

    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });

});

