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
  $http.get("list_blog")
  .then(function(response) {
      $scope.bloglist = response.data;
  });
});
</script>
<h3>Blog List</h3>
 <div ng-app="myApp" ng-controller="ctrl"> 
<table class="tg" border="2">
		<tr>
			<th width="80">Blog ID</th>
			<th width="120">Blog Title</th>
			<th width="120">Blog Description</th>
			<th width="80">For Updation</th>
			<th width="80">For Deletion</th>
			</tr>
			
 <tr ng-repeat="b in bloglist" >
 
 <td >{{ b.id }} </td>
 <td>{{ b.title }}</td>
 <td>{{ b.description }}</td>
 <td><a href="editblog?id={{b.id}}">Edit</a></td>
 <td><a href="manage_blog_remove?id={{ b.id }}" onclick="return confirm('Are you sure?')">Delete</a></td>
  </tr>
 </table>
</div>

</body>
</html>