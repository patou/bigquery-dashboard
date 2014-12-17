app.controller('HeaderCtrl', function($scope, $location, AuthService) {
    AuthService.refresh();

    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };

    $scope.isAuthenticated = AuthService.isAuthenticated();
    $scope.isAdmin = AuthService.isAdmin();
    $scope.user = AuthService.getUser();
    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.isAuthenticated = AuthService.isAuthenticated();
        $scope.isAdmin = AuthService.isAdmin();
        $scope.user = AuthService.getUser();
    });

    $scope.user = AuthService.getUser();
    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.user = AuthService.getUser();
    });

    $scope.loginPath = function() {
        return "/login?path=" + $location.path();
    };
    $scope.logoutPath = function() {
        return "/logout?path=" + $location.path();
    };
});