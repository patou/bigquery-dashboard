app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "templates/home.html",
            controller: "HomeCtrl"
        })
        .when("/dashboard", {
            templateUrl: "templates/dashboard.html",
            controller: "DashboardCtrl"
        })
        .when("/dashboard/:dashId", {
            templateUrl: "templates/dashboard.html",
            controller: "DashboardCtrl"
        })
        .when("/addDashboard", {
            templateUrl: "templates/addDashboard.html",
            controller: "AddDashboardCtrl"
        })
        .when("/requests", {
            templateUrl: "templates/requests.html",
            controller: "RequestsCtrl"
        })
        .when("/addRequest", {
            templateUrl: "templates/editRequest.html",
            controller: "EditRequestCtrl"
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
            templateUrl: "templates/editRequest.html",
            controller: "EditRequestCtrl"
        })

        .otherwise({ redirectTo: '/'});
});