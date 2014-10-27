app.controller('ResultController', function ($http, $scope, $location, $interval, $routeParams, AuthService) {
    AuthService.refresh();
    var loadResult = function (jobId) {
        $scope.startListenResult = function () {
            $interval(function () {
                $http.get("/api/execute/result/" + jobId)
                    .success(function (data) {
                        if (data.jobComplete === true) {
                            $scope.results = eval(data);
                            console.log(data);
                        }
                        else {
                            loadResult(jobId);
                        }
                    })
                    .error(function () {
                        console.log("Erreur durant l'interrogation du job.")
                    });
            }, 1000, 1);
        };
        $scope.startListenResult(jobId);
    }
    var requestCallback = function (reqId) {
        $http.get("/api/service/query/", item)
            .success(function (data) {
                console.log(data);
                $http.post("/api/execute/query/", data.request)
                    .success(function (jobRef) {
                        console.log(jobRef);
                        loadResult(jobRef.jobId);
                    })
                    .error(function (error) {
                        console.log(error);
                    });
            })
            .error(function (error) {
                console.log(error);
            });
    };

    requestCallback($routeParams.reqId);

});