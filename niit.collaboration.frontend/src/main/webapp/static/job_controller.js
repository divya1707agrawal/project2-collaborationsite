'use strict';
 
angular.module('myApp').controller('JobController', ['$scope', 'JobService', function($scope, UserService) {
    var self = this;
    self.job={id:null,title:'',description:'',dateTime:''};
    self.jobs=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllJobs();
 
    function fetchAllJobs(){
        JobService.fetchAllJobs()
            .then(
            function(d) {
                self.jobs = d;
            },
            function(errResponse){
                console.error('Error while fetching Jobs');
            }
        );
    }
 
    function createJob(job){
        JobService.createJob(job)
            .then(
            fetchAllJobs,
            function(errResponse){
                console.error('Error while creating Jobs');
            }
        );
    }
 
    function updateJobs(job, id){
        JobService.updateJob(job, id)
            .then(
            fetchAllJobs,
            function(errResponse){
                console.error('Error while updating Job');
            }
        );
    }
 
    function deleteJob(id){
        JobService.deleteJob(id)
            .then(
            fetchAllJobs,
            function(errResponse){
                console.error('Error while deleting Job');
            }
        );
    }
 
    function submit() {
        if(self.job.id===null){
            console.log('Saving New Job', self.job);
            createJob(self.job);
        }else{
            updateJob(self.job, self.job.id);
            console.log('Job updated with id ', self.job.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.jobs.length; i++){
            if(self.jobs[i].id === id) {
                self.job = angular.copy(self.jobs[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.job.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteJob(id);
    }
 
 
    function reset(){
        self.job={id:null,title:'',description:'',dateTime:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);