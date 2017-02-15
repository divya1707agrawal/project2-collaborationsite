<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>Insert title here</title>
</head>
<body ng-app="myApp" class="ng-cloak" >
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







      <div class="generic-container" ng-controller="BlogController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"><img src='<c:url value='/static/images/image10.jpg'/>'width="970" height="100"/></span></div><br>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.blog.id" />
                    
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="title">Blog Title:</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.blog.title" id="title" class="title form-control input-sm" placeholder="Enter your blog title" required/>
                                </div>
                           </div>
                       </div>
                                
                    <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="description">Description:</label>
                              <div class="col-md-7">
                                  <textarea rows="8" cols="80" ng-model="ctrl.blog.description" id="description" class="form-control input-sm" placeholder="Enter your blog description." required>
                                  </textarea>
                              </div>
                          </div>
                      </div> 
                      
                       <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.blog.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Blog</button>
                          </div>
                      </div>
                      </form>
                       <div class="container">
                  <table class="table">

<tr ng-repeat="u in ctrl.blogs"><td><table><tr>
<td ng-bind="u.dateTime">
{{ u.dataTime |  date:'MM/dd/yyyy'}}

</td></tr>
<tr>
<td><table><tr><td> <img src='<c:url value='/static/images/{{ u.userID }}.jpeg' />' width="75" height="75"/></td></tr>
<tr><td ng-bind="u.title">{{ u.title }}</td><tr>
<tr><td ng-bind="u.description">{{ u.description }}</td></tr>
<tr><td><table>
<th><td><button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button></td> </th>
<th><td> <a href="manage_blog_remove?id={{ u.id }}"  onclick="return confirm('Are you sure?')">Remove</a> </td></th>
 </table></td> </tr>
</table></td><tr></table></td>
</tr></table>
               </div>  
              </div>
          </div></div>



    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/blog_controller.js' />"></script>
      <script src="<c:url value='/static/blog_service.js' />"></script>            
</body>
</html>