app.controller('HeaderController', function($scope, $location, AuthService) {
    AuthService.refresh();
    $scope.user = AuthService.getUser();
    $scope.isActive = function(viewLocation) {
        return viewLocation === $location.path();
    };

    $scope.isAuthenticated = AuthService.isAuthenticated();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function () {
        $scope.isAuthenticated = AuthService.isAuthenticated();
    });

    $scope.isAdmin = AuthService.isAdmin();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function () {
        $scope.isAdmin = AuthService.isAdmin();
    });

    $scope.loginPath = function() {
        return "/login?path=" + $location.path();
    };
    $scope.logoutPath = function() {
        return "/logout?path=" + $location.path();
    };
});