app.controller('BigQueryAdminController', function($scope, $http) {

    $scope.items = {};
    $scope.formDisabled = true;

    $http.get("/api/service/queries")
        .success(function (data) {
            $scope.items = eval(data);
            $scope.formDisabled = false;
        })
        .error(function(error) {
            console.log(error);
        });

    $scope.update = function(item) {
        $http.put("/api/service/query", item)
            .error(function(error) {
                console.log(error);
            });
    };

    $scope.del = function(item) {
        $http.delete("/api/service/query/"+item.id)
            .success(function (data) {
                for (var i = 0; i < $scope.items.length; i++) {
                    if ($scope.items[i].id === item.id) {
                        $scope.items.splice(i, 1);
                        break;
                    }
                };
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
        };

        $scope.formDisabled = true;
        var item = {libelle: $scope.libelleText, request: $scope.requestText};
        $http.put("/api/service/query", item)
            .success(function (data) {
                $scope.items.push(data);
                $scope.libelleText = "";
                $scope.requestText = "";
                $scope.formDisabled = false;
            })
            .error(function(error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };
});