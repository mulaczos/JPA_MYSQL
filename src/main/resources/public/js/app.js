var app = angular.module("formSubmit", [ 'ngRoute' ]);
// var myApp = angular.module('app');

app.controller('CustomersController', [ '$scope', '$http', '$route',
		function($scope, $http, $route) {

			$scope.customerData = [];

			var responseArray = $http.get('/all');

			responseArray.success(function(data, status, headers, config) {
				$scope.customerData = data;
			});
			responseArray.error(function(data, status, headers, config) {
				alert("Exception details: " + JSON.stringify({
					data : data
				}));
			});
		} ]);

app.controller('FormSubmitController', [ '$scope', '$http',
		function($scope, $http) {

			$scope.name = {}
			$scope.submit = function() {

				var response = $http.post('/', {
					"name" : {
						"firstName" : $scope.name.firstName,
						"lastName" : $scope.name.lastName
					}
				});

				response.success(function(data, status, headers, config) {
					$scope.list.push(data);
				});
				response.error(function(data, status, headers, config) {
					alert("Exception details: " + JSON.stringify({
						data : data
					}));
				});
				$scope.list = [];
			};
		} ]);