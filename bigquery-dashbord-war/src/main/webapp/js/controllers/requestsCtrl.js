app.controller('RequestsCtrl', function ($scope, $location, $http, $interval, AuthService) {
    AuthService.refresh();

    $http.get("/api/service/queries")
        .success(function (data) {
            $scope.items = eval(data);
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
        $location.path("/result/"+item.id);
    };
});