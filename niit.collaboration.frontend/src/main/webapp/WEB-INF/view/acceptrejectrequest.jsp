<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<body>
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
   
</ul></div></nav>

<br><br><br>




<script>
var app = angular.module('myApp', []);
app.controller('ctrl', function($scope, $http) {
  $http.get("getRequestsSendToMe")
  .then(function(response) {
      $scope.friend = response.data;
  });
});
</script>

<img src='<c:url value='/static/images/image13.jpg' />'
		align="right" alt="This is image" width="300" height="350">
		<img src='<c:url value='/static/images/image12.jpg' />'
		align="right" alt="This is image" width="450" height="450">
<h3>List Of Get Friend Requests</h3>


 <div ng-app="myApp" ng-controller="ctrl"> 
<table class="tg" border="2">
		<tr>
			<th width="80">FriendID</th>
			<th width="80">Accept Friend Request</th>
			<th width="80">Reject Friend Request</th>
		</tr>
			
 <tr ng-repeat="f in friend" >
 
 <td >{{ f }} </td>
 <td><a href="acceptFriend/{{ f }}">Accept Friend Request</a></td>
  <td><a href="rejectFriend/{{ f }}">Reject Friend Request</a></td>
  </tr>
 </table>
</div>




</body>
</html>