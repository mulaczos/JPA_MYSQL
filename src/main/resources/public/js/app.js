var counter=0;

var app = angular.module("app", [ 'app.services' ]);
app.controller('CustomersController', [
		'$scope',
		'$http',
		'$rootScope',
		'CustomersFactory',
		function($scope, $http, $rootScope, CustomersFactory) {
					$scope.customerData = CustomersFactory.getAll(),
					counter++,
					$scope.counter=counter;
					
			$rootScope.$on("akcja", function() {
				$scope.customerData = CustomersFactory.getAll();

			}, true);

		} ]);

app.controller('FormSubmitController', [
		'$scope',
		'$http',
		'$rootScope',
		'CustomersFactory',
		function($scope, $http, $rootScope, CustomersFactory) {

			$scope.submit = function() {
				CustomersFactory.add({
					name : $scope.name
				}, function(responseData, httpData) {
					$scope.id = responseData.id,
							$scope.firstName = responseData.name.firstName,
							$scope.lastName = responseData.name.lastName,
							
							$rootScope.$broadcast("akcja", {
								id : responseData.id
							});
				});
				$scope.id = "", $scope.firstName = "", $scope.lastName = "";
			};
		} ]);