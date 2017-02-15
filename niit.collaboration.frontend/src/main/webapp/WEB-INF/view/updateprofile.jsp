<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AngularJS $http Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 70%;
      margin: auto;
  }
  </style>
  
  
   <style>
         .Search {
     position: absolute;
     top: 355px;
     left: 575px;
}
.Search input {
     height: 50px;
     width: 180pt;
}
        </style>
  </head>
  
  <body ng-app="myApp" class="ng-cloak">
  <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Collaboration</a>
    </div>
   <ul class="nav navbar-nav">
       <%
   String user=(String)session.getAttribute("loggedInUserID"); 
   if(user==null)
   {
	   out.println("<li class='active'><a href='login'>Login</a></li>");
	   out.println("<li class='active'><a href='user'>Registration</a></li>");
   }
   else
   {
	   out.println("<li class='active'><a href='logout'>Logout</a></li>");
   }
   
   %>
  
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Blogs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="blog">Add New Blog</a></li>
      <li><a href="viewblog">Show List of Blogs</a></li>
    </ul></li>

  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Friends<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="unfriend">View Friends/Unfriend</a></li>
      <li ><a href="viewusers">Send Friend Request</a></li>
    <li ><a href="acceptrejectrequest">Accept/Reject Request</a></li>
    </ul></li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Jobs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="applyforjob">Search Job</a></li>
      <li><a href="job">Post New Jobs</a></li>
    </ul></li>
    
    
    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Chat Forums<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="chatforum">Create Chat Forum</a></li>
      <li><a href="viewchatforums">View Existing Forums</a></li>
    </ul></li>
    <li class="active"><a href="personalchatting">Personal Chat</a></li>
 
    <li class="active"><a href="updateprofile">Update Profile</a></li>
    
</ul></div></nav><br><br><br>
  
  
  
  
  
  
  <script>
var app = angular.module('myApp', []);
app.controller('ctrl', function($scope, $http) {
  $http.get("myProfile")
  .then(function(response) {
      $scope.user = response.data;
  });
});
</script>
<img src='<c:url value='/static/images/image16.jpg' />'
		align="right" alt="This is image" width="400" height="500">
 <div ng-app="myApp" ng-controller="ctrl"> 
<h3>----Profile Updation----</h3>

 <form method="POST" name="myForm"  action="updateProfile" class="form-horizontal">
 <table>
  <input type="hidden" ng-model="user.id" />
  <tr>
  <td>
  <img src='<c:url value='/static/images/{{ user.id }}.jpeg' />'/>
  </td>
  </tr>
<tr><td>Name:</td><td><input type='text' value='{{ user.name }}' name="name" required/></td></tr>
<tr><td>Password:</td><td><input type='password' value='{{ user.password }}' name="password" required/></td></tr>
<tr><td>Address:</td><td><input type='text' value='{{ user.address }}' name="address" required/></td></tr>
<tr><td>Email ID:</td><td><input type='text' value='{{ user.email }}' name="email"  required/></td></tr>
<tr><td>Mobile No:</td><td><input type='text' value='{{ user.mobile }}' name="mobile" pattern = "[0-9]{10}" required/></td></tr>
<tr><td><input type='submit' value='submit'/></td></tr>
</table>
</form>



 </div>
  
     
         
     


</body>
</html>
