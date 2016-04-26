//var app = angular.module("app", [ 'ngRoute' ]);
//
//app.controller('CustomersController', [ '$scope', '$http', '$route', 'CustomersFactory',
//		function($scope, $http, $route, CustomersFactory) {
//
//			$scope.customerData = CustomersFactory.getAll();
//
////			var responseArray = $http.get('/customers/all');
////
////			responseArray.success(function(data, status, headers, config) {
////				$scope.customerData = data;
////			});
////			responseArray.error(function(data, status, headers, config) {
////				alert("Exception details: " + JSON.stringify({
////					data : data
////				}));
////			});
//		} ]);
//
//
//app.controller('FormSubmitController', [ '$scope', '$http',
//		function($scope, $http) {
//
//			$scope.name = {}
//			$scope.submit = function() {
//
//				var response = $http.post('/customers/add', {
//					"name" : {
//						"firstName" : $scope.name.firstName,
//						"lastName" : $scope.name.lastName
//					}
//				});
//
//				response.success(function(data, status, headers, config) {
//					$scope.list.push(data);
//				});
//				response.error(function(data, status, headers, config) {
//					alert("Exception details: " + JSON.stringify({
//						data : data
//					}));
//				});
//				$scope.list = [];
//			};
//		} ]);