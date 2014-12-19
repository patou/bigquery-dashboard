app.controller('ResultCtrl', function ($http, $scope, $interval, $timeout, $routeParams, AuthService, ngProgress) {
    AuthService.refresh();

    $scope.counterMilli = 0;
    $scope.counterSec = 0;

    $scope.onTimeout = function(){
        $scope.counterMilli++;
        if($scope.counterMilli === 10){
            $scope.counterMilli = 0;
            $scope.counterSec++;
        }
        timer = $timeout($scope.onTimeout,100);
    };
    var timer = $timeout($scope.onTimeout,100);


    var loadResult = function (jobId) {
        $scope.startListenResult = function () {
            $interval(function () {
                $http.get("/api/execute/result/" + jobId)
                    .success(function (data) {
                        if (data.jobComplete === true) {
                            $scope.results = eval(data);
                            $timeout.cancel(timer);
                            ngProgress.complete();
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
    };
    var requestCallback = function (reqId) {
        $http.get("/api/service/query/"+reqId)
            .success(function (data) {
                console.log(data);
                $scope.label = data.label;
                $scope.comment = data.comment;
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