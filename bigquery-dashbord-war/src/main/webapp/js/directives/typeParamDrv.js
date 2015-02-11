app.directive('TypeParam', function () { //type-param


    return {
        restrict : "E",
        replace: true,
        transclude: true,
        link : function (scope, attributes) {

        },
        templateUrl : "templates/directiveParamDrv.html"
    }

});
