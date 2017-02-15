<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    
</ul></div></nav><br><br><br>




<script>
var app = angular.module('myApp', []);
app.controller('ctrl', function($scope, $http) {
  $http.get("users")
  .then(function(response) {
      $scope.user = response.data;
  });
});
</script>
<h3>List Of Users</h3>
 <div ng-app="myApp" ng-controller="ctrl"> 
Search Your Friend: <input type='text' ng-model='searchtext'/><br><br>
<table class="tg" border="2">
		<tr>
			<th width="80">UserID</th>
			<th width="120">Name</th>
			
				<th width="120">Email</th>
					<th width="120">Address</th>
						<th width="120">MobileNo.</th>
							<th width="120">Role</th>
						
							<th width="120">For Sending Friend Request</th>
			</tr>
			
 <tr ng-repeat="b in user | filter:searchtext" >
 
 <td >{{ b.id }} </td>
 <td>{{ b.name }}</td>
  <td >{{ b.email }} </td>
   <td >{{ b.address }} </td>
    <td >{{ b.mobile }} </td>
     <td >{{ b.role }} </td>
  
  <td><a href="addFriend/{{b.id}}">Send FriendRequest</a></td>
  </tr>
 </table>
</div>
</body>
</html>