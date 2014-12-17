app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "templates/dashboard.html",
            controller: "DashboardCtrl"
        })
        .when("/dashboard", {
            templateUrl: "templates/dashboard.html",
            controller: "DashboardCtrl"
        })
        .when("/requests", {
            templateUrl: "templates/requests.html",
            controller: "RequestsCtrl"
        })
        .when("/add", {
            templateUrl: "templates/edit.html",
            controller: "EditCtrl"
        })
        .when("/admin", {
            templateUrl: "templates/admin.html",
            controller: "AdminCtrl"
        })
        .when("/result/:reqId", {
            templateUrl: "templates/result.html",
            controller: "ResultCtrl"
        })
        .when("/edit/:reqId", {
            templateUrl: "templates/edit.html",
            controller: "EditCtrl"
        })

        .otherwise({ redirectTo: '/'});
});