app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "templates/home.html",
            controller: "HomeController"
        })
        .when("/admin", {
            templateUrl: "templates/admin.html",
            controller: "BigQueryAdminController"
        })
        .when("/result/:reqId", {
            templateUrl: "templates/result.html",
            controller: "ResultController"
        })
        .otherwise({ redirectTo: '/'});
});