<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>
<script>
var app = angular.module('myApp', []);
app.controller('ctrl', function($scope, $http) {
  $http.get("getRequestsSendToMe")
  .then(function(response) {
      $scope.friend = response.data;
  });
});
</script>
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

<script>
var app = angular.module('myApp1', []);
app.controller('ctrl1', function($scope, $http) {
  $http.get("myFriends")
  .then(function(response) {
      $scope.friends = response.data;
  });
});
</script>
<h3>List Of My Friends</h3>
 <div ng-app="myApp1" ng-controller="ctrl1"> 
<table class="tg" border="2">
		<tr>
			<th width="80">UserID</th>
			<th width="120">Name</th>
			
				<th width="120">Email</th>
					<th width="120">Address</th>
						<th width="120">MobileNo.</th>
							<th width="120">Role</th>
						
							<th width="120">For UnFriend</th>
			</tr>
			
 <tr ng-repeat="f in friends" >
 
 <td >{{ f.id }} </td>
 <td>{{ f.name }}</td>
  <td >{{ f.email }} </td>
   <td >{{ f.address }} </td>
    <td >{{ f.mobile }} </td>
     <td >{{ f.role }} </td>
    
  <td><a href="unFriend/{{ f }}">UnFriend</a></td>
  </tr>
 </table>
</div>


</body>
</html>