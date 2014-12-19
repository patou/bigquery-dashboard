app.controller('AddDashboardCtrl', function($scope, $http, AuthService) {
    AuthService.refresh();
    $scope.$watch(function () { return AuthService.getUser(); }, function () {
        $scope.user = AuthService.getUser();
    });
    $scope.addNewItem = function () {
        var item = {label: $scope.labelText, id: $scope.id, icons: $scope.icons, user: $scope.user.id};
        $http.put("/api/service/dashboard", item)
            .success(function (data) {
                $scope.items = eval(data);
                $scope.labelText = "";
                $scope.commentText = "";
                $scope.icons = "";
                $scope.formDisabled = false;
                window.location.href = '/#/';
            })
            .error(function (error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };

});
