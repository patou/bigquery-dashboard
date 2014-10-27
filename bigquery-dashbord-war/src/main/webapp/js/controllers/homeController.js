app.controller('HomeController', function($scope, $location, AuthService, $http, ngProgress) {
    ngProgress.reset();
    ngProgress.start();
    AuthService.refresh();
    $scope.user = AuthService.getUser();
    $scope.items = {};
    $scope.resuls = {};

    $http.get("/api/service/queries")
        .success(function (data) {
            $scope.items = eval(data);
            ngProgress.complete();
        })
        .error(function(error) {
            console.log(error);
        });

    $scope.loginPath = function() {
        return "/login?path=" + $location.path();
    };

    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.user = AuthService.getUser();
    });

    $scope.isAuthenticated = AuthService.isAuthenticated();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function () {
        $scope.isAuthenticated = AuthService.isAuthenticated();
    });

    $scope.isAdmin = AuthService.isAdmin();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function () {
        $scope.isAdmin = AuthService.isAdmin();
    });


    $scope.launchRequest = function (index){
        var item = $scope.items[index].request;
        ngProgress.reset();
        ngProgress.start();
        $http.post("/api/execute/query/",item)
            .success(function (data){
                console.log(data);
                ngProgress.complete();
                requestCallback(data.jobId);
            })
            .error(function(error){
                console.log(error);
            });
    };

    var requestCallback = function (jobId) {
        var stop;
        $scope.fight = function () {
            if (angular.isDefined(stop)) return;

            stop = $interval(function () {
                $http.get("/api/execute/result/"+jobId)
                    .success(function (data) {
                        if(data.jobComplete === true){
                            $scope.stopFight();
                            $scope.resuls = eval(data);

                        }
                    })
                    .error(function (error) {

                    });
            }, 1000);
        };
        $scope.stopFight = function() {
            if (angular.isDefined(stop)) {
                $interval.cancel(stop);
                stop = undefined;
            }
        };
    };
});