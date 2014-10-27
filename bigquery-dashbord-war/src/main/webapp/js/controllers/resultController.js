app.controller('ResultController', function ($scope, $location, $interval, $routeParams, AuthService) {
    AuthService.refresh();
    var requestCallback = function (jobId) {
        $scope.startListenResult = function () {
            $interval(function () {
                $http.get("/api/execute/result/" + jobId)
                    .success(function (data) {
                        if (data.jobComplete === true) {
                            $scope.resuls = eval(data);
                            console.log(data);
                        }
                        else {
                            requestCallback(jobId);
                        }
                    })
                    .error(function () {
                        console.log("Erreur durant l'interrogation du job.")
                    });
            }, 1000, 1);
        };
        $scope.startListenResult(jobId);
    };

    requestCallback($routeParams.jobId);

});