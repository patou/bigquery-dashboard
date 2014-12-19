app.controller('HeaderCtrl', function($scope, $http, $location, AuthService) {
    AuthService.refresh();

    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };

    $scope.user = AuthService.getUser();
    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.isAuthenticated = AuthService.isAuthenticated();
        $scope.isAdmin = AuthService.isAdmin();
        $scope.user = AuthService.getUser();
        if($scope.user) {
            $http.get("/api/service/dashboard/user/" + $scope.user.id)
                .success(function (data) {
                    $scope.dashboards = eval(data);
                })
                .error(function (error) {
                    console.log(error);
                });
        }
    });

    $scope.loginPath = function() {
        return "/login?path=" + $location.path();
    };
    $scope.logoutPath = function() {
        return "/logout?path=" + $location.path();
    };

});