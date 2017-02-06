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
			<th width="80">ID</th>
			<th width="80">UserID</th>
			<th width="80">FriendID</th>
			<th width="80">Status</th>
			<th width="80">IsOnline</th>
			
							<th width="120">For UnFriend</th>
			</tr>
			
 <tr ng-repeat="f in friends" >
 
 <td >{{ f.id }} </td>
 <td>{{ f.userID }}</td>
 <td>{{ f.friendID }}</td>
 <td>{{ f.status }}</td>
 <td>{{ f.isOnline }}</td>

  <td><a href="unFriend/{{ f.friendID }}">UnFriend</a></td>
  </tr>
 </table>
</div>
</body>
</html>