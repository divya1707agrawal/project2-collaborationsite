<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/comment_controller.js' />"></script>
      <script src="<c:url value='/static/comment_service.js' />"></script>
       <script>
function getParameter(theParameter) { 
	  var params = window.location.search.substr(1).split('&');
	 
	  for (var i = 0; i < params.length; i++) {
	    var p=params[i].split('=');
		if (p[0] == theParameter) {
			
		  return decodeURIComponent(p[1]);
		}
	  }
	  return false;
	}
	
var param=getParameter('blogid')

myForm.d.value = "My value";
	</script>
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
<body  ng-app="myApp" class="ng-cloak">
<nav class="navbar navbar-invers navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Collaboration</a>
    </div>
   <ul class="nav navbar-nav">
      <li class="active"><a href="login">Login</a></li>
      <li class="active"><a href="user">Registration</a></li>
      
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
    <li class="active"><a href="user/logout">Logout</a></li>

</ul></div></nav>





      <div class="generic-container" ng-controller="CommentController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"><h3>-----Add Blog page-----</h3></span></div><br><br>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.comment.id" />
 <div class="row">
 
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="description">Description:</label>
                              <div class="col-md-7">
                                  <textarea rows="8" cols="80" ng-model="ctrl.comment.description" id="description" class="form-control input-sm" placeholder="Enter your blog description. [This field is validation free]">
                                  </textarea>
                              </div>
                          </div>
                      </div> <br>
                      
                       <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="rating">Rating:</label>
                              <div class="col-md-7">
                     <select ng-model="ctrl.comment.rating" id="rating">
      <option>1</option> 
      <option>2</option>
   <option>3</option>
   <option>4</option>
   <option>5</option>
   </select> </div>
                          </div>
                      </div>
                    <br>  
                       <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.comment.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Blog</button>
                          </div>
                      </div>
                      </form>
                      <br><br>
                       <table class="tg" border="2">
<tr>
<th> ID</th>
<th>Blog comments</th>
<th>Rating</th>
</tr>
<tr ng-repeat="u in ctrl.comments">
<td ng-bind="u.id">{{ u.id }}</td>

<td ng-bind="u.description">{{ u.description }}</td>
<td ng-bind="u.rating">{{ u.rating }}</td>
                      
</tr>
</table>
                 
              </div>
          </div></div>

                      
                      
                      
</body>
</html>