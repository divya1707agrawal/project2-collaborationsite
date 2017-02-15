'use strict';
 
angular.module('myApp').factory('ChatForumService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/addforum/';
    var REST_SERVICE_update_URI = 'http://localhost:8080/niit.collaboration.frontend/manage_chat_forum/';
    var REST_SERVICE_fetch_URI = 'http://localhost:8080/niit.collaboration.frontend/list_chat_forum/';
    var factory = {
        fetchAllChatForums: fetchAllChatForums,
        createChatForum: createChatForum,
        updateChatForum:updateChatForum,
    
    };
 
    return factory;
 
    function fetchAllChatForums() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_fetch_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching ChatForums');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createChatForum(chatforum) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, chatforum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating ChatForum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateChatForum(chatforum, id) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_update_URI+id, chatforum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating ChatForum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
   
 
}]);