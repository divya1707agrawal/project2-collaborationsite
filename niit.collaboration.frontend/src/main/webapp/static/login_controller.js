'use strict';
 
angular.module('myApp').controller('LoginController', ['$scope', 'LoginService', function($scope, LoginService) {
    var self = this;
    self.user={id:'',password:''};
    self.users=[];
 
    self.submit = submit;
    self.reset = reset;
    
    function createUser(user){
     LoginService.createUser(user);
   
    }
 
    function submit() {
       
            console.log('Saving New User', self.user);
            createUser(self.user);
            

    }
 
    function reset(){
        self.user={id:'',password:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);