app.controller('EditCtrl', function ($http, $scope, $location, $interval, $routeParams, AuthService) {
    AuthService.refresh();
    $scope.items = {};
    $scope.result = {};
    $scope.formDisabled = true;
    $scope.isRunning = false;
    $scope.params = [];
    $scope.status = {
        isOk: "",
        message: ""
    };
    $scope.editorOptions = {
        lineWrapping : true,
        lineNumbers: true,
        indentWithTabs: true,
        smartIndent: true,
        lineNumbers: true,
        matchBrackets : true,
        autofocus: true,
        extraKeys: {"Ctrl-Space": "autocomplete"},
        mode: 'text/x-sql'
    };

    $scope.$watch('requestText', function() {
        var myRe = /@[^ .']+/g;
        var str = $scope.requestText;
        var myArray;
        $scope.params = [];
        while ((myArray = myRe.exec(str)) != null){
            var content = myArray[0];
            $scope.params.push(removeAt(content));
        }
    });

    if ($routeParams.reqId) {

        $http.get("/api/service/query/" + $routeParams.reqId)
            .success(function (data) {
                $scope.formDisabled = false;
                $scope.labelText = data.label;
                $scope.commentText = data.comment;
                $scope.requestText = data.request;
                $scope.icons = data.icons;
                $scope.id = data.id;
            });
    }

    $scope.addNewItem = function () {
        var item = {label: $scope.labelText, request: $scope.requestText, comment: $scope.commentText, id: $scope.id, icons: $scope.icons};
        $http.put("/api/service/query", item)
            .success(function (data) {
                $scope.items = eval(data);
                $scope.labelText = "";
                $scope.commentText = "";
                $scope.requestText = "";
                $scope.icons = "";
                $scope.formDisabled = false;
                window.location.href = '/#/admin';
            })
            .error(function (error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };

    $scope.cancel = function () {
        $scope.labelText = "";
        $scope.commentText = "";
        $scope.requestText = "";
        $scope.icons = "";
        $scope.formDisabled = false;
        window.location.href = '/#/admin';
    };

    $scope.test = function () {
        $scope.isRunning = true;
        var item = {label: $scope.labelText, request: $scope.requestText, comment: $scope.commentText, id: $scope.id, icons: $scope.icons};
        $http.post("/api/execute/query/try", item.request)
            .success(function () {
                $scope.status.message = "Requête valide";
                $scope.status.isOk = true;
                $scope.isRunning = false;
            })
            .error(function (error) {
                $scope.status.message = error;
                $scope.status.isOk = false;
                $scope.isRunning = false;
            });

    };

    var removeAt = function(value) {
        return value.substring(1, value.length);
    };

});