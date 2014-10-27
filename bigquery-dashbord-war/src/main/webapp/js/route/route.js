app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "templates/home.html",
            controller: "HomeController"
        })
        .when("/admin", {
            templateUrl: "templates/admin.html",
            controller: "BigQueryAdminController"
        })
        .when("/result", {
            templateUrl: "templates/result.html",
            controller: "ResultController"
        })
        .otherwise({ redirectTo: '/'});
});