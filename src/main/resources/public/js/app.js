var app = angular.module("formSubmit", []);
//var myApp = angular.module('app');

app.controller('FormSubmitController', [ '$scope', '$http',  function($scope, $http, $location) {

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

app.controller('CustomersController', ['$scope','$http', function($scope, $http) {

	$scope.customerData=[];
	
	var responseArray = $http.get('/all');
	
	responseArray.success(function(data, status, headers, config) {
		$scope.customerData=data;

	});
	responseArray.error(function(data, status, headers, config) {
		alert("Exception details: " + JSON.stringify({
			data : data
		}));
	});	
}]);
