'use strict';
 
angular.module('myApp').controller('CommentController', ['$scope', 'CommentService', function($scope, CommentService) {
    var self = this;
    self.comment={id:null,forumId:'',description:'',rating:''};
    self.comments=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllComments();
 
    function fetchAllComments(){
        CommentService.fetchAllComments()
            .then(
            function(d) {
                self.comments = d;
            },
            function(errResponse){
                console.error('Error while fetching Comments');
            }
        );
    }
 
    function createComment(comment){
        CommentService.createComment(comment)
            .then(
            fetchAllComments,
            function(errResponse){
                console.error('Error while creating Comment');
            }
        );
    }
 
    function updateComment(comment, id){
        CommentService.updateComment(comment, id)
            .then(
            fetchAllComments,
            function(errResponse){
                console.error('Error while updating Comment');
            }
        );
    }
 
    function deleteComment(id){
        CommentService.deleteComment(id)
            .then(
            fetchAllComments,
            function(errResponse){
                console.error('Error while deleting Comment');
            }
        );
    }
 
    function submit() {
        if(self.comment.id==null){
            console.log('Saving New Comment', self.comment);
            createComment(self.comment);
        }else{
            updateComment(self.comment, self.comment.id);
            console.log('Comment updated with id ', self.comment.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.comments.length; i++){
            if(self.comments[i].id === id) {
                self.comment = angular.copy(self.comments[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.comment.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteComment(id);
    }
 
 
    function reset(){
        self.comment={id:null,forumId:'',description:'',rating:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);