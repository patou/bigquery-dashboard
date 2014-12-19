app.controller('AdminCtrl', function($scope, $http) {
    $scope.formDisabled = true;

    $http.get("/api/service/queries/all")
        .success(function (data) {
            $scope.items = eval(data);
            $scope.formDisabled = false;
        })
        .error(function(error) {
            console.log(error);
        });

    $scope.update = function(item) {
        window.location = "/#/edit/"+item.id;
    };

    $scope.del = function(item) {
        $http.delete("/api/service/query/"+item.id)
            .success(function () {
                for (var i = 0; i < $scope.items.length; i++) {
                    if ($scope.items[i].id === item.id) {
                        $scope.items.splice(i, 1);
                        break;
                    }
                }
            })
            .error(function(error) {
                console.log(error);
            });
    };
});