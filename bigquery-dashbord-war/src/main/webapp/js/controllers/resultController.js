app.controller('ResultController', function($scope, $location, AuthService) {
    AuthService.refresh();
    window.alert($scope.items.length);

});