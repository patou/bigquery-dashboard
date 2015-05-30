//TODO use param object instead of flat string.
app.controller('EditRequestCtrl', function ($http, $scope, $location, $interval, $routeParams, AuthService) {
    AuthService.refresh();
    $scope.item = {label: "", request: "SELECT * FROM [project.tablename]", comment: "", id: undefined, icons: '', params: []};
    $scope.formDisabled = true;
    $scope.isRunning = false;
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
    $scope.changeItemName = function (newValue, oldValue) {
        // access new and old value here
        $scope.item.request = $scope.item.request.replace("@"+oldValue, "@"+newValue);
    };

    if ($routeParams.reqId) {
        $http.get("/api/service/query/" + $routeParams.reqId)
            .success(function (data) {
                $scope.formDisabled = false;
                $scope.item = data;
            });
    }

    $scope.addNewItem = function () {
        $http.put("/api/service/query", $scope.item)
            .success(function (data) {
                $location.path("/admin");
            })
            .error(function (error) {
                console.log(error);
                $scope.formDisabled = false;
            });
    };

    $scope.cancel = function () {
        $scope.item = item = {label: "", request: "SELECT * FROM [project.tablename]", comment: "", id: undefined, icons: '', params: []};
        $scope.formDisabled = false;
        $location.path("/admin");
    };

    $scope.test = function () {
        $scope.isRunning = true;
        $http.post("/api/execute/query/try", $scope.item.request)
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
            var defaultParam = $scope.item.params[index].defaultValue;
            if (!defaultParam)
                defaultParam = "";
            $scope.item.request = $scope.item.request.replace("@"+$scope.item.params[index].name, defaultParam);
            $scope.item.params = $scope.item.params.splice(index, 1);
        }
    };

    $scope.itemText = function (type) {
        switch (type) {
            case 'TEXT': return "Text";
            case 'DATE': return "Date";
            case 'LIST': return "List";
            case 'NUMBER': return "Number";
        }
    }

    $scope.changeItemType = function (param, type) {
        param.type = type;
        switch (type) {
            case 'TEXT':
                param.defaultValue = "";
                break;
            case 'DATE':
                param.defaultValue = null;
                break;
            case 'LIST':
                param.values = [];
                break;
            case 'NUMBER':
                param.defaultValue = 0;
                break;
        }
    }

    $scope.addParam = function() {
        var item = Math.random().toString(36).substring(7);
        $scope.item.params.push({name:item, type:'TEXT'}); //TODO assign a logical text here.
        $scope.item.request += " @"+item;
    };

    var removeAt = function(value) {
        return value.substring(1, value.length);
    };

});