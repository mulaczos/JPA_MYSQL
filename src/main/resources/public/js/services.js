var services = angular.module('app.services', ['ngResource']);

var baseUrl = '';

services.factory('CustomersFactory', function ($resource) {
	return $resource(baseUrl + '/customers', {}, {
		add: {method:'POST', url: baseUrl + '/customers/add'},
		getAll: {method: 'GET', url: baseUrl + '/customers/all', isArray: true}
	})
});