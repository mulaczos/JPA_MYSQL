var app = angular.module("formSubmit", []);






	app.controller('FormSubmitController', [
			'$scope',
			'$http',
			'$location',
			
			function($scope, $http, $location) {

				$scope.name = {}
				$scope.submit = function() {

					var formData = {
						"name" : {
							"firstName" : $scope.name.firstName,
							"lastName" : $scope.name.lastName
						}
					};
					
				
							var response = $http.post('/', formData);
					
						response.success(function(data, status, headers, config) {
							$scope.list.push(data);
							
						});
						
						response.error(function(data, status, headers, config) {
							alert("Exception details: " + JSON.stringify({
								data : data
							}));
						});
//						$scope.body = $location.path('add'),
						
						//Empty list data after process
						$scope.list = [];
//						
						
						

				};
				
			} ]);