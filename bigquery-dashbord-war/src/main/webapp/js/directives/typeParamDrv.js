app.directive('TypeParam', function () { //type-param
    return {
        restrict : "E",
        replace: true,
        transclude: true,
        scope: {
          param: '='
        },
        link : function (scope, attributes) {
            console.log(scope);
            console.log(attributes);
        },
        templateUrl : "templates/typeParamDrv.html"
    }
});
