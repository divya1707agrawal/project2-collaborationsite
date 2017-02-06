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
  $http.get("users")
  .then(function(response) {
      $scope.user = response.data;
  });
});
</script>
<h3>List Of Users</h3>
 <div ng-app="myApp" ng-controller="ctrl"> 
 <input type='text' ng-model='searchtext'/>
<table class="tg" border="2">
		<tr>
			<th width="80">UserID</th>
			<th width="120">Name</th>
			
				<th width="120">Email</th>
					<th width="120">Address</th>
						<th width="120">MobileNo.</th>
							<th width="120">Role</th>
							<th width="120">Is Online</th>
							<th width="120">For Sending Friend Request</th>
			</tr>
			
 <tr ng-repeat="b in user | filter:searchtext" >
 
 <td >{{ b.id }} </td>
 <td>{{ b.name }}</td>
  <td >{{ b.email }} </td>
   <td >{{ b.address }} </td>
    <td >{{ b.mobile }} </td>
     <td >{{ b.role }} </td>
     <td >{{ b.isOnline }}</td>
  <td><a href="addFriend/{{b.id}}">Send FriendRequest</a></td>
  </tr>
 </table>
</div>
</body>
</html>