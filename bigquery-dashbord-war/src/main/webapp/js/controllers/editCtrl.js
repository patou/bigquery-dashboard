app.controller('EditCtrl', function ($http, $scope, $location, $interval, $routeParams, AuthService) {
    AuthService.refresh();
    $scope.items = {};
    $scope.result = {};
    $scope.formDisabled = true;

    if($routeParams.reqId){

        $http.get("/api/service/query/"+$routeParams.reqId)
            .success(function(data){
                $scope.formDisabled = false;
                $scope.labelText = data.label;
                $scope.commentText = data.comment;
                $scope.requestText = data.request;
                $scope.id = data.id;
            });
    }

    $scope.addNewItem = function() {
        var item = {label: $scope.labelText, request: $scope.requestText, comment: $scope.commentText, id: $scope.id};
        $http.put("/api/service/query", item)
            .success(function (data) {
                $scope.items = eval(data);
                $scope.labelText = "";
                $scope.commentText = "";
                $scope.requestText = "";
                $scope.formDisabled = false;
                window.location.href = '/#/admin';
            })
            .error(function(error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };

    $scope.cancel = function() {
        $scope.labelText = "";
        $scope.commentText = "";
        $scope.requestText = "";
        $scope.formDisabled = false;
        window.location.href = '/#/admin';
    };

});