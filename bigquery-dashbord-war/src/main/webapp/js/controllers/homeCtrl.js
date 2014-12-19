app.controller('HomeCtrl', function ($scope, $http, AuthService) {
    AuthService.refresh();
    $scope.user = AuthService.getUser();
    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.user = AuthService.getUser();
    });

});