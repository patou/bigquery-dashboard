app.controller('HomeController', function($scope, $location, AuthService, $http, ngProgress) {
    ngProgress.reset();
    ngProgress.start();
    AuthService.refresh();
    $scope.user = AuthService.getUser();
    $scope.items = {};

    $http.get("/api/service/queries")
        .success(function (data, status, headers, config) {
            $scope.items = eval(data);
            ngProgress.complete();
        })
        .error(function(error) {
            console.log(error);
        });

    $scope.loginPath = function() {
        return "/login?path=" + $location.path();
    };

    $scope.$watch(function () { return AuthService.getUser(); }, function (newVal, oldVal) {
        $scope.user = AuthService.getUser();
    });

    $scope.isAuthenticated = AuthService.isAuthenticated();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
        $scope.isAuthenticated = AuthService.isAuthenticated();
    });

    $scope.isAdmin = AuthService.isAdmin();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
        $scope.isAdmin = AuthService.isAdmin();
    });


    $scope.launchRequest = function (index){
        var item = $scope.items[index].request;
        ngProgress.reset();
        ngProgress.start();
        $http.post("/api/execute/query/",item)
            .success(function (data, status, headers, config){
                console.log(data);
                ngProgress.complete();
            })
            .error(function(error){
                console.log(error);
            });
    };
});