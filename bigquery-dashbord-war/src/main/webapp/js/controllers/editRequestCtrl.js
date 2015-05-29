//TODO use param object instead of flat string.
app.controller('EditRequestCtrl', function ($http, $scope, $location, $interval, $routeParams, AuthService) {
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
        matchBrackets : true,
        autofocus: true,
        extraKeys: {"Ctrl-Space": "autocomplete"},
        mode: 'text/x-sql'
    };

    //$scope.$watch('requestText', function() {
    //    var myRe = /@[^ .']+/g;
    //    var str = $scope.requestText;
    //    var myArray;
    //    while ((myArray = myRe.exec(str)) != null){
    //        var content = myArray[0];
    //        $scope.params.push({name:removeAt(content), type:'TEXT'});
    //    }
    //});

    if ($routeParams.reqId) {
        $http.get("/api/service/query/" + $routeParams.reqId)
            .success(function (data) {
                $scope.formDisabled = false;
                $scope.labelText = data.label;
                $scope.commentText = data.comment;
                $scope.requestText = data.request;
                $scope.icons = data.icons;
                $scope.id = data.id;
                $scope.params = data.params;
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
                $location.path("/admin");
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
        $location.path("/admin");
    };

    $scope.test = function () {
        $scope.isRunning = true;
        var item = {label: $scope.labelText, request: $scope.requestText, comment: $scope.commentText, id: $scope.id, icons: $scope.icons, params: $scope.params};
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

    $scope.removeParam = function(index) {
        if (index > -1) {
            var defaultParam = $scope.params.defaultValue; //TODO retrieve the default value of the current param to be replaced.
            if (!defaultParam)
                defaultParam = "";
            $scope.params.splice(index+1, 1);
            $scope.requestText = $scope.requestText.replace("@"+$scope.params[index].name, defaultParam);
        }
    };

    $scope.addParam = function() {
        var item = Math.random().toString(36).substring(7);
        $scope.params.push({name:item, type:'TEXT'}); //TODO assign a logical text here.
        $scope.requestText = $scope.requestText + " @"+item;
    };

    var removeAt = function(value) {
        return value.substring(1, value.length);
    };

});