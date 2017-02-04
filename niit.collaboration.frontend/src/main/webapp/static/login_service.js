'use strict';
 
angular.module('myApp').factory('LoginService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/user/authenticate/';
 
    var factory = {
        createUser: createUser,
    
    };
 
    return factory;
 
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
            function (response) {
                console.log(response.data.errorCode);
                console.log('response data');
               
                       if(response.data.errorCode==200)
                    	   {
                    	   window.location="homepage";
                    	   }
                       else
                    	   {
                    	   console.log(response.data.erroMessage);
                    	   }
                deferred.resolve(response.data);
          
            
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
}]);