app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl: "templates/home.html",
            controller: 'HomeController'
        })
        .when('/admin', {
            templateUrl: "templates/admin.html",
            controller: 'BigQueryAdminController'
        })
        .otherwise({ redirectTo: '/'});
});