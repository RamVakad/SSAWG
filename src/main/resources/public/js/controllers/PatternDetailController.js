var PatternDetailModule = angular.module("PatternDetailModule", ['AuthModule', 'SliderMenuModule', 'DataModule']);

PatternDetailModule.controller('PatternDetailController', function($route, $http, $window, $scope, $location, $routeParams, AuthFactory, DataFetcher) {
    if (AuthFactory.isLoggedIn() === false) $location.url('/login'); //Not logged in? Send user back.
    $scope.dirScope = { };
    $scope.dirScope.PAGE = "PATTERNDETAIL"
    $scope.dirScope.USERNAME = AuthFactory.getUsername();
    $scope.providedPatID = $routeParams.id;

    $scope.isAdmin = AuthFactory.isAdmin();
    $scope.isApprover = AuthFactory.isApprover();



    DataFetcher.getPatternById($scope.providedPatID, function(ret) {
        if (ret.success) {
            $scope.pattern = ret.data;
            console.log(JSON.stringify($scope.pattern))
            $scope.pattern.classification = $scope.pattern.classification.replace('CLASS_', '');
            $scope.pattern.securityDomain = $scope.pattern.securityDomain.replace('SEC_', '');
        } else {
            $location.url('/internalerror')
        }
    })

    $scope.save = function () {
        //console.log(JSON.stringify($scope.pattern));
        if ($scope.pattern.approverComments === '') {
            $scope.pattern.approverComments = ' ';
        }
        $scope.pattern.classification = "CLASS_" + $scope.pattern.classification;
        $scope.pattern.securityDomain = "SEC_" + $scope.pattern.securityDomain;
        $http({
            method: 'POST',
            url: '/api/patterns/save',
            data: JSON.stringify($scope.pattern),
            headers: {
                'Content-Type': 'application/json',
                "Accept": "*/*"
            }
        }).then(function (success) {
            $window.alert("Pattern Saved Successfully.");
            $route.reload();
        }, function (error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }

    $scope.approve = function () {
        if ($scope.pattern.approverComments === '') {
            $scope.pattern.approverComments = ' ';
        }
        //console.log(JSON.stringify($scope.pattern));
        $scope.pattern.classification = "CLASS_" + $scope.pattern.classification;
        $scope.pattern.securityDomain = "SEC_" + $scope.pattern.securityDomain;
        $http({
            method: 'POST',
            url: '/api/patterns/approve/' + $scope.pattern.id,
            data: $scope.pattern.approverComments,
            headers: {
                'Content-Type': 'text/html',
                "Accept": "*/*"
            }
        }).then(function (success) {
            $window.alert("Pattern Approved Successfully.");
            $location.url('/approved')
        }, function (error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }

    $scope.reject = function () {
        if ($scope.pattern.approverComments === '') {
            $scope.pattern.approverComments = ' ';
        }
        //console.log(JSON.stringify($scope.pattern));
        $scope.pattern.classification = "CLASS_" + $scope.pattern.classification;
        $scope.pattern.securityDomain = "SEC_" + $scope.pattern.securityDomain;
        $http({
            method: 'POST',
            url: '/api/patterns/reject/' + $scope.pattern.id,
            data: $scope.pattern.approverComments,
            headers: {
                'Content-Type': 'text/html',
                "Accept": "*/*"
            }
        }).then(function (success) {
            $window.alert("Pattern Rejected Successfully.");
            $location.url('/rejected')
        }, function (error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }

    $scope.downloadFile = function(id) {
        $http.get('/api/attachments/get/' + id, {  }, { }
        ).then(function (response) {

            var attmt = response.data;
            console.log("retreived attachment " + attmt.fileName);
            console.log("bitsInBytes " + attmt.bitsInBytes);
            attmt.bitsInBytes = Uint8Array.from(attmt.bitsInBytes);
            var filename = attmt.fileName;
            var contentType = attmt.contentType;
            console.log(attmt.contentType);
            var success = false;

            // Determine the content type from the header or default to "application/octet-stream"

            try
            {
                // Try using msSaveBlob if supported
                console.log("Trying saveBlob method ...");
                var blob = new Blob([attmt.bitsInBytes], { type: contentType });
                if(navigator.msSaveBlob)
                    navigator.msSaveBlob(blob, filename);
                else {
                    // Try using other saveBlob implementations, if available
                    var saveBlob = navigator.webkitSaveBlob || navigator.mozSaveBlob || navigator.saveBlob;
                    if(saveBlob === undefined) throw "Not supported";
                    saveBlob(blob, filename);
                }
                console.log("saveBlob succeeded");
                success = true;
            } catch(ex)
            {
                console.log("saveBlob method failed with the following exception:");
                console.log(ex);
            }

            if(!success)
            {
                // Get the blob url creator
                var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
                if(urlCreator)
                {
                    // Try to use a download link
                    var link = document.createElement('a');
                    if('download' in link)
                    {
                        // Try to simulate a click
                        try
                        {
                            // Prepare a blob URL
                            console.log("Trying download link method with simulated click ...");
                            var blob = new Blob([attmt.bitsInBytes], { type: contentType });
                            var url = urlCreator.createObjectURL(blob);
                            link.setAttribute('href', url);

                            // Set the download attribute (Supported in Chrome 14+ / Firefox 20+)
                            link.setAttribute("download", filename);

                            // Simulate clicking the download link
                            var event = document.createEvent('MouseEvents');
                            event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
                            link.dispatchEvent(event);
                            console.log("Download link method with simulated click succeeded");
                            success = true;

                        } catch(ex) {
                            console.log("Download link method with simulated click failed with the following exception:");
                            console.log(ex);
                        }
                    }

                    if(!success)
                    {
                        // Fallback to window.location method
                        try
                        {
                            // Prepare a blob URL
                            // Use application/octet-stream when using window.location to force download
                            console.log("Trying download link method with window.location ...");
                            var blob = new Blob([attmt.bitsInBytes], { type: octetStreamMime });
                            var url = urlCreator.createObjectURL(blob);
                            window.location = url;
                            console.log("Download link method with window.location succeeded");
                            success = true;
                        } catch(ex) {
                            console.log("Download link method with window.location failed with the following exception:");
                            console.log(ex);
                        }
                    }

                }
            }

            if(!success)
            {
                // Fallback to window.open method
                console.log("No methods worked for saving the arraybuffer, using last resort window.open");
                window.open(httpPath, '_blank', '');
            }


        }, function(error) {
            $location.url('/internalerror')
            console.log(error);
        });
    }


    $scope.$on('$routeChangeSuccess', function () {
        var footerHeight  = angular.element( document.getElementById('footer11') ).outerHeight();
        //var headerHeight  = angular.element( document.getElementById('header11') ).innerHeight();
        var windowHeight  = $(window).height();

        $('.content-wrapper').css('min-height', windowHeight - footerHeight)
    });
});
