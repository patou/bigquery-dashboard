app.controller('BigQueryAdminController', function($scope, $http, ngProgress) {
    ngProgress.reset();
    ngProgress.start();
    $scope.items = {};
    $scope.formDisabled = true;

    $http.get("/api/service/queries/all")
        .success(function (data) {
            ngProgress.complete();
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
        ngProgress.reset();
        ngProgress.start();
        $http.delete("/api/service/query/"+item.id)
            .success(function () {
                for (var i = 0; i < $scope.items.length; i++) {
                    if ($scope.items[i].id === item.id) {
                        $scope.items.splice(i, 1);
                        break;
                    }
                }
                ngProgress.complete();
            })
            .error(function(error) {
                console.log(error);
            });
    };
});