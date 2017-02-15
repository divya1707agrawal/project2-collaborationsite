'use strict';
 
angular.module('myApp').factory('CommentService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/list_forum_comment/';
    var REST_SERVICE_update_URI = 'http://localhost:8080/niit.collaboration.frontend/manage_forum_comment/';
    var REST_SERVICE_create_URI = 'http://localhost:8080/niit.collaboration.frontend/addforumcomment/';
    var factory = {
        fetchAllComments: fetchAllComments,
        createComment: createComment,
        updateComment:updateComment,
    
    };
 
    return factory;
 
    function fetchAllComments() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Comments');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createComment(comment) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_create_URI, comment)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Comment');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateComment(comment, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_update_URI+id, comment)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Comment');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
   
 
}]);