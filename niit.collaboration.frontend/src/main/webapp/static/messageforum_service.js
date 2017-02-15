'use strict';
 
angular.module('myApp').factory('MessageForumService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/addMessageforum/';
    var REST_SERVICE_fetch_URI = 'http://localhost:8080/niit.collaboration.frontend/list_message_forum/';
    var factory = {
        fetchAllMessageForums: fetchAllMessageForums,
        createMessageForum: createMessageForum,
       
    };
 
    return factory;
 
    function fetchAllMessageForums() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_fetch_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching MessageForums');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createMessageForum(messageforum) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, messageforum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating MessageForum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);