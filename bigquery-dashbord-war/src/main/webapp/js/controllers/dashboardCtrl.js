app.controller('DashboardCtrl', function ($scope, $http, $routeParams, $location) {
    $http.get("/api/service/dashboard/" + $routeParams.dashId)
        .success(function (data) {
            $scope.dashboard = eval(data);
        })
        .error(function (error) {
            console.log(error);
        });

    $scope.deleteDash = function(id) {
        $http.delete("/api/service/dashboard/" + id)
            .success(function (data) {
                $scope.dashboard = eval(data);
                $location.path("/");
            })
            .error(function (error) {
                console.log(error);
            });
    };

});
