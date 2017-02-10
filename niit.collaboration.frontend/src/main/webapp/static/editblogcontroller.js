angular.module('myApp').controller('editcontroller', function($scope, $http) {
	  $http.post("getblog")
	  .then(function(response) {
	      $scope.blog = response.data;
	  });
	});