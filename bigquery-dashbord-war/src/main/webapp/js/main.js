var app = angular.module('appEngineAngularDemo', [
	   'ngRoute',
	   'auth'
   ]);

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

app.controller('HeaderController', function($scope, $location, AuthService) {
	AuthService.refresh();
	
	$scope.isActive = function(viewLocation) { 
	    return viewLocation === $location.path();
	};
	
	$scope.isAuthenticated = AuthService.isAuthenticated();
	$scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
		$scope.isAuthenticated = AuthService.isAuthenticated();
	});

    $scope.isAdmin = AuthService.isAdmin();
    $scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
        $scope.isAdmin = AuthService.isAdmin();
    });
	
	$scope.loginPath = function() {
		return "/login?path=" + $location.path();
	};
	$scope.logoutPath = function() {
		return "/logout?path=" + $location.path();
	};
});

app.controller('HomeController', function($scope, $location, AuthService, $http) {
	AuthService.refresh();
	
	$scope.user = AuthService.getUser();
    $scope.items = {};

    $http.get("/bigquery")
        .success(function (data, status, headers, config) {
            $scope.items = eval(data);
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
});

app.controller('BigQueryAdminController', function($scope, $http) {
	
	$scope.items = {};
	$scope.formDisabled = true;
	
	$http.get("/bigquery")
		.success(function (data, status, headers, config) {
			$scope.items = eval(data);
			$scope.formDisabled = false;
	    })
	    .error(function(error) {
		 	console.log(error);
		});
	
	$scope.update = function(item) {
		$http.put("/bigquery", item)
			.error(function(error) {
				console.log(error);
			});
	};
	
	$scope.del = function(item) {
		$http.delete("/bigquery/"+item.id)
			.success(function (data, status, headers, config) {
			    for (var i = 0; i < $scope.items.length; i++) {
			        if ($scope.items[i].id === item.id) {
			        	$scope.items.splice(i, 1);
			        	break;
			        }
			    };
		    })
			.error(function(error) {
				console.log(error);
			});
	};

	$scope.addNewItem = function() {
	    for (var i = 0; i < $scope.items.length; i++) {
	        if ($scope.items[i].libelle.toUpperCase() === $scope.libelle.toUpperCase()) {
	        	alert($scope.libelleText + " already exists!");
	        	return;
	        }
	    };
		
		$scope.formDisabled = true;
		var item = {libelle: $scope.libelleText, request: $scope.requestText};
		$http.put("/bigquery", item)
			.success(function (data, status, headers, config) {
				$scope.items.push(item);
                $scope.libelleText = "";
				$scope.formDisabled = false;
		    })
			.error(function(error) {
				console.log(error);
				$scope.formDisabled = false;
			});
	};
});
