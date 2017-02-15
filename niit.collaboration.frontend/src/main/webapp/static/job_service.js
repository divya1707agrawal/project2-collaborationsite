'use strict';
 
angular.module('myApp').factory('JobService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/niit.collaboration.frontend/postAJob';
 
    var factory = {
        fetchAllJobs: fetchAllJobs,
        createJob: createJob,
        updateJob:updateJob,
        deleteJob:deleteJob    
    };
 
    return factory;
 
    function fetchAllJobs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Jobs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createJob(job) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, job)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Job');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateJob(job, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"/update", job)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Job');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteJob(id) {
        var deferred = $q.defer();
        $http['delete'](REST_SERVICE_URI+"/delete",id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);