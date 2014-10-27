app.controller('HomeController', function ($scope, $location, $http, $interval, AuthService, ngProgress) {
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
        .error(function (error) {
            console.log(error);
        });

    $scope.loginPath = function () {
        return "/login?path=" + $location.path();
    };

    $scope.$watch(function () {
        return AuthService.getUser();
    }, function () {
        $scope.user = AuthService.getUser();
    });

    $scope.isAuthenticated = AuthService.isAuthenticated();
    $scope.$watch(function () {
        return AuthService.isAuthenticated();
    }, function () {
        $scope.isAuthenticated = AuthService.isAuthenticated();
    });

    $scope.isAdmin = AuthService.isAdmin();
    $scope.$watch(function () {
        return AuthService.isAuthenticated();
    }, function () {
        $scope.isAdmin = AuthService.isAdmin();
    });


    $scope.launchRequest = function (index) {
        var item = $scope.items[index];
        ngProgress.reset();
        ngProgress.start();

        $location.path("/result/"+item.id);
    };
});