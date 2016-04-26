var app = angular.module("app", [ 'app.services' ]);

app.controller('CustomersController', [ '$scope', '$http', 'CustomersFactory',
		function($scope, $http, CustomersFactory) {

			$scope.customerData = CustomersFactory.getAll();

		} ]);

app.controller('FormSubmitController', [ '$scope', '$http', 'CustomersFactory',
		function($scope, $http, CustomersFactory) {
	
			$scope.submit = function() {
				CustomersFactory.add({
					name : {
						firstName : $scope.name.firstName,
						lastName : $scope.name.lastName
					}
				}, function(responseData, httpData) {
					$scope.id=responseData.id,
					$scope.firstName=responseData.name.firstName,
					$scope.lastName=responseData.name.lastName;
				} );
				$scope.id="",
				$scope.firstName="",
				$scope.lastName="";
			};
			
		} ]);