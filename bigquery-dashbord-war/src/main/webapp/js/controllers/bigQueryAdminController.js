app.controller('BigQueryAdminController', function($scope, $http, ngProgress) {
    ngProgress.reset();
    ngProgress.start();
    $scope.items = {};
    $scope.formDisabled = true;

    $http.get("/api/service/queries")
        .success(function (data) {
            ngProgress.complete();
            $scope.items = eval(data);
            $scope.formDisabled = false;
        })
        .error(function(error) {
            console.log(error);
        });

    $scope.update = function(item) {
        ngProgress.reset();
        ngProgress.start();
        $http.put("/api/service/query", item)
            .success(function (){
                ngProgress.complete();
            })
            .error(function(error) {
                console.log(error);
            });

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

    $scope.addNewItem = function() {
        for (var i = 0; i < $scope.items.length; i++) {
            if ($scope.items[i].libelle.toUpperCase() === $scope.libelleText.toUpperCase()) {
                alert($scope.libelleText + " existe déjà!");
                return;
            }
        }
        ngProgress.reset();
        ngProgress.start();
        $scope.formDisabled = true;
        var item = {libelle: $scope.libelleText, request: $scope.requestText};
        $http.put("/api/service/query", item)
            .success(function (data) {
                $scope.items.push(data);
                $scope.libelleText = "";
                $scope.requestText = "";
                $scope.formDisabled = false;
                ngProgress.complete();
            })
            .error(function(error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };
});