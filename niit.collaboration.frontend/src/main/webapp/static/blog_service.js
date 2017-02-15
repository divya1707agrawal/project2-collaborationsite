'use strict';
 
angular.module('myApp').factory('BlogService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/addblog/';
    var REST_SERVICE_Update_URI = 'http://localhost:8080/niit.collaboration.frontend/manage_blog';
    var REST_SERVICE_Fetch_URI = 'http://localhost:8080/niit.collaboration.frontend/list_user_blog/';
    var factory = {
        fetchAllBlogs: fetchAllBlogs,
        createBlog: createBlog,
        updateBlog:updateBlog,
    
    };
 
    return factory;
 
    function fetchAllBlogs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_Fetch_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Blogs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createBlog(blog) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateBlog(blog, id) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_Update_URI, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
   
 
}]);